package fun.imiku.napcat4j.dispatcher;

import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import com.mikuac.shiro.dto.event.message.MessageEvent;
import com.mikuac.shiro.dto.event.message.PrivateMessageEvent;
import fun.imiku.napcat4j.annotation.message.GroupMessageListener;
import fun.imiku.napcat4j.annotation.message.PrivateMessageListener;
import fun.imiku.napcat4j.listener.MessageListener;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class MessageEventDispatcher {

    private final ApplicationContext applicationContext;
    private final ExecutorService virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();

    private List<ListenerBinding<PrivateMessageEvent>> privateListeners = List.of();
    private List<ListenerBinding<GroupMessageEvent>> groupListeners = List.of();

    public MessageEventDispatcher(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        this.privateListeners = scanPrivateMessageListeners();
        this.groupListeners = scanGroupMessageListeners();
        log.info("消息调度器加载完毕，{} 个私聊监听器，{} 个群聊监听器", privateListeners.size(), groupListeners.size());
    }

    @PreDestroy
    public void shutdown() {
        virtualThreadExecutor.close();
    }

    public void dispatch(Bot bot, PrivateMessageEvent event) {
        if (event.getPostType().equals("message_sent")) {
            dispatchSent(bot, event);
            return;
        }
        if (privateListeners.isEmpty()) {
            return;
        }
        long now = Instant.now().getEpochSecond();
        for (ListenerBinding<PrivateMessageEvent> binding : privateListeners) {
            dispatchAsync(bot, event, binding, now);
        }
    }

    public void dispatch(Bot bot, GroupMessageEvent event) {
        if (event.getPostType().equals("message_sent")) {
            dispatchSent(bot, event);
            return;
        }
        if (groupListeners.isEmpty()) {
            return;
        }
        long now = Instant.now().getEpochSecond();
        for (ListenerBinding<GroupMessageEvent> binding : groupListeners) {
            dispatchAsync(bot, event, binding, now);
        }
    }

    public void dispatchSent(Bot bot, PrivateMessageEvent event) {
    }

    public void dispatchSent(Bot bot, GroupMessageEvent event) {
    }

    private <T extends MessageEvent> void dispatchAsync(Bot bot, T event, ListenerBinding<T> binding, long now) {
        if (!shouldProcessByTime(now, event, binding.ignoreSeconds())) {
            return;
        }
        virtualThreadExecutor.execute(() -> {
            try {
                T filteredEvent = binding.listener().filter(bot, event);
                if (filteredEvent == null) {
                    return;
                }
                binding.listener().process(bot, filteredEvent);
            } catch (Exception e) {
                log.error("消费聊天消息失败 listener={}, message={}",
                        binding.listener().getClass().getName(), event.getMessage(), e);
            }
        });
    }

    private boolean shouldProcessByTime(long now, MessageEvent event, int ignoreSeconds) {
        if (ignoreSeconds == -1) {
            return true;
        }
        Long eventTime = event.getTime();
        if (eventTime == null) {
            return false;
        }
        return Math.abs(now - eventTime) <= ignoreSeconds;
    }

    @SuppressWarnings("unchecked")
    private List<ListenerBinding<PrivateMessageEvent>> scanPrivateMessageListeners() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(PrivateMessageListener.class);
        List<ListenerBinding<PrivateMessageEvent>> bindings = new ArrayList<>(beans.size());
        for (Object bean : beans.values()) {
            PrivateMessageListener annotation = AnnotationUtils.findAnnotation(
                    AopUtils.getTargetClass(bean), PrivateMessageListener.class);
            if (annotation == null) {
                continue;
            }
            if (!(bean instanceof MessageListener<?> listener)) {
                log.error("@PrivateMessageListener bean does not implement MessageListener: {}", bean.getClass().getName());
                continue;
            }
            bindings.add(new ListenerBinding<>((MessageListener<PrivateMessageEvent>) listener, annotation.value()));
        }
        return List.copyOf(bindings);
    }

    @SuppressWarnings("unchecked")
    private List<ListenerBinding<GroupMessageEvent>> scanGroupMessageListeners() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(GroupMessageListener.class);
        List<ListenerBinding<GroupMessageEvent>> bindings = new ArrayList<>(beans.size());
        for (Object bean : beans.values()) {
            GroupMessageListener annotation = AnnotationUtils.findAnnotation(
                    AopUtils.getTargetClass(bean), GroupMessageListener.class);
            if (annotation == null) {
                continue;
            }
            if (!(bean instanceof MessageListener<?> listener)) {
                log.error("@GroupMessageListener bean does not implement MessageListener: {}", bean.getClass().getName());
                continue;
            }
            bindings.add(new ListenerBinding<>((MessageListener<GroupMessageEvent>) listener, annotation.value()));
        }
        return List.copyOf(bindings);
    }

    private record ListenerBinding<T extends MessageEvent>(MessageListener<T> listener, int ignoreSeconds) {
    }
}

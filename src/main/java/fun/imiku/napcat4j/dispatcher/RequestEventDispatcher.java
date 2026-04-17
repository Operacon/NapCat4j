package fun.imiku.napcat4j.dispatcher;

import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.request.FriendAddRequestEvent;
import com.mikuac.shiro.dto.event.request.GroupAddRequestEvent;
import com.mikuac.shiro.dto.event.request.RequestEvent;
import fun.imiku.napcat4j.annotation.request.FriendRequestListener;
import fun.imiku.napcat4j.annotation.request.GroupRequestListener;
import fun.imiku.napcat4j.listener.RequestListener;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class RequestEventDispatcher {

    private final ApplicationContext applicationContext;
    private final ExecutorService virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();

    private List<ListenerBinding<FriendAddRequestEvent>> friendListeners = List.of();
    private List<ListenerBinding<GroupAddRequestEvent>> groupListeners = List.of();

    public RequestEventDispatcher(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        this.friendListeners = scanFriendRequestListeners();
        this.groupListeners = scanGroupRequestListeners();
        log.info("请求调度器加载完毕，{} 个好友监听器, {} 个群监听器",
                friendListeners.size(), groupListeners.size());
    }

    @PreDestroy
    public void shutdown() {
        virtualThreadExecutor.close();
    }

    public void dispatch(Bot bot, FriendAddRequestEvent event) {
        for (ListenerBinding<FriendAddRequestEvent> binding : friendListeners) {
            dispatchAsync(bot, event, binding);
        }
    }

    public void dispatch(Bot bot, GroupAddRequestEvent event) {
        for (ListenerBinding<GroupAddRequestEvent> binding : groupListeners) {
            dispatchAsync(bot, event, binding);
        }
    }

    private <T extends RequestEvent> void dispatchAsync(Bot bot, T event, ListenerBinding<T> binding) {
        virtualThreadExecutor.execute(() -> {
            try {
                T filteredEvent = binding.listener().filter(bot, event);
                if (filteredEvent == null) {
                    return;
                }
                binding.listener().process(bot, filteredEvent);
            } catch (Exception e) {
                log.error("Request event consume failed listener={}, type={}",
                        binding.listener().getClass().getName(), event.getRequestType(), e);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private List<ListenerBinding<FriendAddRequestEvent>> scanFriendRequestListeners() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(FriendRequestListener.class);
        List<ListenerBinding<FriendAddRequestEvent>> bindings = new ArrayList<>(beans.size());
        for (Object bean : beans.values()) {
            FriendRequestListener annotation = AnnotationUtils.findAnnotation(
                    AopUtils.getTargetClass(bean), FriendRequestListener.class);
            if (annotation == null) {
                continue;
            }
            if (!(bean instanceof RequestListener<?> listener)) {
                log.error("@FriendRequestListener bean does not implement RequestListener: {}",
                        bean.getClass().getName());
                continue;
            }
            bindings.add(new ListenerBinding<>((RequestListener<FriendAddRequestEvent>) listener));
        }
        return List.copyOf(bindings);
    }

    @SuppressWarnings("unchecked")
    private List<ListenerBinding<GroupAddRequestEvent>> scanGroupRequestListeners() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(GroupRequestListener.class);
        List<ListenerBinding<GroupAddRequestEvent>> bindings = new ArrayList<>(beans.size());
        for (Object bean : beans.values()) {
            GroupRequestListener annotation = AnnotationUtils.findAnnotation(
                    AopUtils.getTargetClass(bean), GroupRequestListener.class);
            if (annotation == null) {
                continue;
            }
            if (!(bean instanceof RequestListener<?> listener)) {
                log.error("@GroupRequestListener bean does not implement RequestListener: {}",
                        bean.getClass().getName());
                continue;
            }
            bindings.add(new ListenerBinding<>((RequestListener<GroupAddRequestEvent>) listener));
        }
        return List.copyOf(bindings);
    }

    private record ListenerBinding<T extends RequestEvent>(RequestListener<T> listener) {
    }
}

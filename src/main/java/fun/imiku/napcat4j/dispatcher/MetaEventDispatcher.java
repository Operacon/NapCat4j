package fun.imiku.napcat4j.dispatcher;

import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.meta.HeartbeatMetaEvent;
import com.mikuac.shiro.dto.event.meta.LifecycleMetaEvent;
import com.mikuac.shiro.dto.event.meta.MetaEvent;
import fun.imiku.napcat4j.annotation.meta.HeartbeatListener;
import fun.imiku.napcat4j.annotation.meta.LifeCycleListener;
import fun.imiku.napcat4j.listener.MetaListener;
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
public class MetaEventDispatcher {

    private final ApplicationContext applicationContext;
    private final ExecutorService virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();

    private List<ListenerBinding<HeartbeatMetaEvent>> heartbeatListeners = List.of();
    private List<ListenerBinding<LifecycleMetaEvent>> lifecycleListeners = List.of();

    public MetaEventDispatcher(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        this.heartbeatListeners = scanHeartbeatListeners();
        this.lifecycleListeners = scanLifecycleListeners();
        log.info("元事件调度器加载完毕, {} heartbeat 监听器, {} lifecycle 监听器",
                heartbeatListeners.size(), lifecycleListeners.size());
    }

    @PreDestroy
    public void shutdown() {
        virtualThreadExecutor.close();
    }

    public void dispatch(Bot bot, HeartbeatMetaEvent event) {
        if (heartbeatListeners.isEmpty()) {
            return;
        }
        long now = Instant.now().getEpochSecond();
        for (ListenerBinding<HeartbeatMetaEvent> binding : heartbeatListeners) {
            dispatchAsync(bot, event, binding, now);
        }
    }

    public void dispatch(Bot bot, LifecycleMetaEvent event) {
        if (lifecycleListeners.isEmpty()) {
            return;
        }
        long now = Instant.now().getEpochSecond();
        for (ListenerBinding<LifecycleMetaEvent> binding : lifecycleListeners) {
            dispatchAsync(bot, event, binding, now);
        }
    }

    private <T extends MetaEvent> void dispatchAsync(Bot bot, T event, ListenerBinding<T> binding, long now) {
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
                log.error("消费元事件失败 listener={}, type={}",
                        binding.listener().getClass().getName(), event.getClass().getSimpleName(), e);
            }
        });
    }

    private boolean shouldProcessByTime(long now, MetaEvent event, int ignoreSeconds) {
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
    private List<ListenerBinding<HeartbeatMetaEvent>> scanHeartbeatListeners() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(HeartbeatListener.class);
        List<ListenerBinding<HeartbeatMetaEvent>> bindings = new ArrayList<>(beans.size());
        for (Object bean : beans.values()) {
            HeartbeatListener annotation = AnnotationUtils.findAnnotation(
                    AopUtils.getTargetClass(bean), HeartbeatListener.class);
            if (annotation == null) {
                continue;
            }
            if (!(bean instanceof MetaListener<?> listener)) {
                log.error("@HeartbeatListener bean does not implement MetaListener: {}", bean.getClass().getName());
                continue;
            }
            bindings.add(new ListenerBinding<>((MetaListener<HeartbeatMetaEvent>) listener, annotation.value()));
        }
        return List.copyOf(bindings);
    }

    @SuppressWarnings("unchecked")
    private List<ListenerBinding<LifecycleMetaEvent>> scanLifecycleListeners() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(LifeCycleListener.class);
        List<ListenerBinding<LifecycleMetaEvent>> bindings = new ArrayList<>(beans.size());
        for (Object bean : beans.values()) {
            LifeCycleListener annotation = AnnotationUtils.findAnnotation(
                    AopUtils.getTargetClass(bean), LifeCycleListener.class);
            if (annotation == null) {
                continue;
            }
            if (!(bean instanceof MetaListener<?> listener)) {
                log.error("@LifeCycleListener bean does not implement MetaListener: {}", bean.getClass().getName());
                continue;
            }
            bindings.add(new ListenerBinding<>((MetaListener<LifecycleMetaEvent>) listener, annotation.value()));
        }
        return List.copyOf(bindings);
    }

    private record ListenerBinding<T extends MetaEvent>(MetaListener<T> listener, int ignoreSeconds) {
    }
}

package fun.imiku.napcat4j.annotation.meta;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记生命周期元事件监听器类。
 * <p>
 * This annotation can only be used on classes implementing
 * {@code fun.imiku.napcat4j.listener.MetaListener<com.mikuac.shiro.dto.event.meta.LifecycleMetaEvent>}.
 */
@Documented
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LifeCycleListener {

    /**
     * 事件过期阈值，当前时间与事件时间相差超过 value 秒的事件将被忽略。
     * <p>
     * 默认 5 秒，设置为 -1 表示禁用。
     *
     * @return allowed time difference in seconds
     */
    int value() default 5;
}

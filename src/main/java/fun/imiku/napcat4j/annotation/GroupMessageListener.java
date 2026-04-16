package fun.imiku.napcat4j.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解为群聊监听器类
 * <p>
 * This annotation can only be used on classes.
 * 被注解的类应当实现
 * {@code fun.imiku.napcat4j.listener.MessageListener<com.mikuac.shiro.dto.event.message.GroupMessageEvent>}.
 */
@Documented
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GroupMessageListener {

    /**
     * 事件过期阈值，与当前时间相差超过 value 秒的事件将被丢弃，减少大量历史积压事件的影响
     * <p>
     * 默认 5 秒，设置为 -1 以禁用
     *
     * @return allowed time difference in seconds
     */
    int value() default 5;
}

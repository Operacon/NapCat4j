package fun.imiku.napcat4j.annotation.notice;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记群名称变更通知监听器类。
 * <p>
 * 被标记的类应实现
 * {@code fun.imiku.napcat4j.listener.NoticeListener<com.mikuac.shiro.dto.event.notice.GroupNameNoticeEvent>}.
 */
@Documented
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GroupNameNoticeListener {

    /**
     * 事件过期阈值（秒），默认 5，设置为 -1 表示禁用时效校验。
     */
    int ignoreAfterSec() default 5;
}


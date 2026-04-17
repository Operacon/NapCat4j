package fun.imiku.napcat4j.annotation.notice;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记群灰字提示通知监听器类。
 * <p>
 * 被标记的类应实现
 * {@code fun.imiku.napcat4j.listener.NoticeListener<com.mikuac.shiro.dto.event.notice.GroupGrayTipNoticeEvent>}.
 */
@Documented
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GroupGrayTipNoticeListener {

    /**
     * 事件过期阈值（秒），默认 5，设置为 -1 表示禁用时效校验。
     */
    int value() default 5;
}


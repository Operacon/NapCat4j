package fun.imiku.napcat4j.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解为好友请求监听器类
 * <p>
 * This annotation can only be used on classes implementing
 * {@code fun.imiku.napcat4j.listener.RequestListener<com.mikuac.shiro.dto.event.request.FriendAddRequestEvent>}.
 */
@Documented
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FriendRequestListener {
}

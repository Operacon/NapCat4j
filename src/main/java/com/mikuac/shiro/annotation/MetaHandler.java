package com.mikuac.shiro.annotation;

import com.mikuac.shiro.enums.MetaEventEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * 使用@MetaHandler 仅支持参数(Bot bot, HeartbeatMetaEvent event) 或 (Bot bot, LifecycleMetaEvent event)
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface MetaHandler {
    MetaEventEnum type() default MetaEventEnum.HEARTBEAT;
}

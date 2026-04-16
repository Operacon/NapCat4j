package fun.imiku.napcat4j.listener;

import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.MessageEvent;

/**
 * Generic message listener contract.
 *
 * @param <T> message event type
 */
public interface MessageListener<T extends MessageEvent> {

    /**
     * 消息过滤器，同时可以对消息进行修改
     *
     * @return 返回原始或修改后的消息 {@code T} 以处理，否则返回 {@code null}
     */
    default T filter(Bot bot, T event) {
        return event;
    }

    /**
     * 消息处理逻辑
     */
    void process(Bot bot, T event);
}

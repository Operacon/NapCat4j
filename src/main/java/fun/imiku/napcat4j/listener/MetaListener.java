package fun.imiku.napcat4j.listener;

import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.meta.MetaEvent;

/**
 * Generic meta event listener contract.
 *
 * @param <T> meta event type
 */
public interface MetaListener<T extends MetaEvent> {

    /**
     * 元事件过滤器，同时可以对元事件进行修改。
     *
     * @return 返回原始或修改后的事件 {@code T} 以处理，否则返回 {@code null}
     */
    default T filter(Bot bot, T event) {
        return event;
    }

    /**
     * 元事件处理逻辑。
     */
    void process(Bot bot, T event);
}


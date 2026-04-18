package fun.imiku.napcat4j.listener;

import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.notice.NoticeEvent;

/**
 * 通知事件监听器通用契约。
 *
 * @param <T> 通知事件类型
 */
public interface NoticeListener<T extends NoticeEvent> {

    /**
     * 通知事件过滤器，同时可对事件进行修改。
     *
     * @return 返回原始或修改后的事件继续处理，返回 {@code null} 表示丢弃
     */
    default T filter(Bot bot, T event) {
        return event;
    }

    /**
     * 通知事件处理逻辑。
     */
    void process(Bot bot, T event) throws Exception;
}

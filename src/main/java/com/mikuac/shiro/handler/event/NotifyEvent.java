package com.mikuac.shiro.handler.event;

import com.mikuac.shiro.common.utils.EventUtils;
import com.mikuac.shiro.common.utils.JsonObjectWrapper;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.core.BotPlugin;
import com.mikuac.shiro.dto.event.notice.GroupGrayTipNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupHonorChangeNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupLuckyKingNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupNameNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupPokeNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupTitleNoticeEvent;
import com.mikuac.shiro.dto.event.notice.InputStatusNoticeEvent;
import com.mikuac.shiro.dto.event.notice.ProfileLikeNoticeEvent;
import com.mikuac.shiro.enums.NotifyEventEnum;
import com.mikuac.shiro.handler.injection.InjectionHandler;
import fun.imiku.napcat4j.dispatcher.NoticeEventDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author zero
 */
@Component
public class NotifyEvent {

    /**
     * 存储通知事件处理器
     */
    public final Map<String, BiConsumer<Bot, JsonObjectWrapper>> handlers = new HashMap<>();
    private final EventUtils utils;
    private final InjectionHandler injection;

    @Autowired
    private NoticeEventDispatcher napcat4jDispatcher;

    @Autowired
    public NotifyEvent(EventUtils utils, InjectionHandler injection) {
        this.utils = utils;
        this.injection = injection;
    }

    /**
     * 通知事件分发
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void handler(Bot bot, JsonObjectWrapper resp) {
        String type = resp.getString("sub_type");
        handlers.getOrDefault(type, (b, e) -> {
        }).accept(bot, resp);
    }

    /**
     * 事件处理
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     * @param type {@link NotifyEventEnum}
     */
    @SuppressWarnings({"ResultOfMethodCallIgnored", "squid:S2201"})
    private void process(Bot bot, JsonObjectWrapper resp, NotifyEventEnum type) {
        if (type == NotifyEventEnum.POKE) {
            GroupPokeNoticeEvent event = resp.to(GroupPokeNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            // 如果群号不为空则作为群内戳一戳处理
            if (event.getGroupId() != null && event.getGroupId() > 0L) {
                injection.invokeGroupPokeNotice(bot, event);
                bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onGroupPokeNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
            } else {
                injection.invokePrivatePokeNotice(bot, event);
                bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onPrivatePokeNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
            }
        }

        if (type == NotifyEventEnum.HONOR) {
            GroupHonorChangeNoticeEvent event = resp.to(GroupHonorChangeNoticeEvent.class);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onGroupHonorChangeNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NotifyEventEnum.LUCKY_KING) {
            GroupLuckyKingNoticeEvent event = resp.to(GroupLuckyKingNoticeEvent.class);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onGroupLuckyKingNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NotifyEventEnum.GROUP_NAME_CHANGE) {
            GroupNameNoticeEvent event = resp.to(GroupNameNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onGroupNameChangeNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NotifyEventEnum.GROUP_TITLE_CHANGE) {
            GroupTitleNoticeEvent event = resp.to(GroupTitleNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onGroupTitleChangeNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NotifyEventEnum.GRAY_TIP) {
            GroupGrayTipNoticeEvent event = resp.to(GroupGrayTipNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onGrayTipNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NotifyEventEnum.PROFILE_LIKE) {
            ProfileLikeNoticeEvent event = resp.to(ProfileLikeNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onProfileLikeNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NotifyEventEnum.INPUT_STATUS) {
            InputStatusNoticeEvent event = resp.to(InputStatusNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onInputStatus(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }
    }

    /**
     * 戳一戳事件
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void poke(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NotifyEventEnum.POKE);
    }

    /**
     * 抢红包运气王事件
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void luckyKing(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NotifyEventEnum.LUCKY_KING);
    }

    /**
     * 群荣誉变更事件
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void honor(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NotifyEventEnum.HONOR);
    }

    /**
     * 群名变更事件
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void groupName(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NotifyEventEnum.GROUP_NAME_CHANGE);
    }

    /**
     * 群名变更事件
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void groupTitle(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NotifyEventEnum.GROUP_TITLE_CHANGE);
    }

    /**
     * 灰条消息事件
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void grayTip(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NotifyEventEnum.GRAY_TIP);
    }

    /**
     * 个人资料点赞事件
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void profileLike(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NotifyEventEnum.PROFILE_LIKE);
    }

    /**
     * 输入状态事件
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void inputStatus(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NotifyEventEnum.INPUT_STATUS);
    }
}

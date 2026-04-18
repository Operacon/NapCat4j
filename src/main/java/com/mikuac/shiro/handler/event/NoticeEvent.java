package com.mikuac.shiro.handler.event;

import com.mikuac.shiro.common.utils.EventUtils;
import com.mikuac.shiro.common.utils.JsonObjectWrapper;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.core.BotPlugin;
import com.mikuac.shiro.dto.event.notice.BotOfflineEvent;
import com.mikuac.shiro.dto.event.notice.ChannelCreatedNoticeEvent;
import com.mikuac.shiro.dto.event.notice.ChannelDestroyedNoticeEvent;
import com.mikuac.shiro.dto.event.notice.ChannelUpdatedNoticeEvent;
import com.mikuac.shiro.dto.event.notice.FriendAddNoticeEvent;
import com.mikuac.shiro.dto.event.notice.FriendRecallNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupAdminNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupBanNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupCardNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupDecreaseNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupEssenceNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupIncreaseNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupMessageReactionNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupMsgEmojiLikeNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupRecallNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupUploadNoticeEvent;
import com.mikuac.shiro.dto.event.notice.MessageReactionsUpdatedNoticeEvent;
import com.mikuac.shiro.dto.event.notice.ReceiveOfflineFilesNoticeEvent;
import com.mikuac.shiro.enums.NoticeEventEnum;
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
public class NoticeEvent {

    /**
     * 存储通知事件处理器
     */
    public final Map<String, BiConsumer<Bot, JsonObjectWrapper>> handlers = new HashMap<>();
    private final EventUtils utils;
    private final NotifyEvent notify;
    private final InjectionHandler injection;
    private final NoticeEventDispatcher napcat4jDispatcher;

    @Autowired
    public NoticeEvent(
            EventUtils eventUtils, NotifyEvent notifyEvent, InjectionHandler injectionHandler,
            NoticeEventDispatcher napcat4jDispatcher
    ) {
        this.utils = eventUtils;
        this.notify = notifyEvent;
        this.injection = injectionHandler;
        this.napcat4jDispatcher = napcat4jDispatcher;
    }

    /**
     * 通知事件分发
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void handler(Bot bot, JsonObjectWrapper resp) {
        String type = resp.getString("notice_type");
        handlers.getOrDefault(
                type,
                (b, e) -> {
                }
        ).accept(bot, resp);
    }

    /**
     * 事件处理
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     * @param type {@link NoticeEventEnum}
     */
    @SuppressWarnings({"ResultOfMethodCallIgnored", "squid:S2201", "squid:S3776"})
    private void process(Bot bot, JsonObjectWrapper resp, NoticeEventEnum type) {
        if (type == NoticeEventEnum.GROUP_UPLOAD) {
            GroupUploadNoticeEvent event = resp.to(GroupUploadNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            injection.invokeGroupUploadNotice(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onGroupUploadNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.GROUP_ADMIN) {
            GroupAdminNoticeEvent event = resp.to(GroupAdminNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            injection.invokeGroupAdmin(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onGroupAdminNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.GROUP_DECREASE) {
            GroupDecreaseNoticeEvent event = resp.to(GroupDecreaseNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            injection.invokeGroupDecrease(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onGroupDecreaseNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.GROUP_INCREASE) {
            GroupIncreaseNoticeEvent event = resp.to(GroupIncreaseNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            injection.invokeGroupIncrease(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onGroupIncreaseNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.GROUP_BAN) {
            GroupBanNoticeEvent event = resp.to(GroupBanNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            injection.invokeGroupBanNotice(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onGroupBanNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.FRIEND_ADD) {
            FriendAddNoticeEvent event = resp.to(FriendAddNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            injection.invokeFriendAdd(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onFriendAddNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.GROUP_MSG_DELETE) {
            GroupRecallNoticeEvent event = resp.to(GroupRecallNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            injection.invokeGroupRecall(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onGroupMsgDeleteNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.PRIVATE_MSG_DELETE) {
            FriendRecallNoticeEvent event = resp.to(FriendRecallNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            injection.invokeFriendRecall(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onPrivateMsgDeleteNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.GROUP_CARD_CHANGE) {
            GroupCardNoticeEvent event = resp.to(GroupCardNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            injection.invokeGroupCardChangeNotice(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onGroupCardChangeNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.GROUP_MESSAGE_REACTION) {
            GroupMessageReactionNoticeEvent event = resp.to(GroupMessageReactionNoticeEvent.class);
            injection.invokeGroupReactionNotice(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onGroupReactionNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.MESSAGE_EMOJI_LIKE) {
            GroupMsgEmojiLikeNoticeEvent event = resp.to(GroupMsgEmojiLikeNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            injection.invokeMessageEmojiLikeNotice(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onMessageEmojiLikeNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.OFFLINE_FILE) {
            ReceiveOfflineFilesNoticeEvent event = resp.to(ReceiveOfflineFilesNoticeEvent.class);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onReceiveOfflineFilesNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.CHANNEL_CREATED) {
            ChannelCreatedNoticeEvent event = resp.to(ChannelCreatedNoticeEvent.class);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onChannelCreatedNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.CHANNEL_DESTROYED) {
            ChannelDestroyedNoticeEvent event = resp.to(ChannelDestroyedNoticeEvent.class);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onChannelDestroyedNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.CHANNEL_UPDATED) {
            ChannelUpdatedNoticeEvent event = resp.to(ChannelUpdatedNoticeEvent.class);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onChannelUpdatedNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.MESSAGE_REACTIONS_UPDATED) {
            MessageReactionsUpdatedNoticeEvent event = resp.to(MessageReactionsUpdatedNoticeEvent.class);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onMessageReactionsUpdatedNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.ESSENCE) {
            GroupEssenceNoticeEvent event = resp.to(GroupEssenceNoticeEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onEssenceNotice(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }

        if (type == NoticeEventEnum.BOT_OFFLINE) {
            BotOfflineEvent event = resp.to(BotOfflineEvent.class);
            napcat4jDispatcher.dispatch(bot, event);
            bot.getPluginList().stream().anyMatch(o -> utils.getPlugin(o).onOffline(bot, event) == BotPlugin.MESSAGE_BLOCK);
        }
    }

    /**
     * 群文件上传
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void groupUpload(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.GROUP_UPLOAD);
    }

    /**
     * 群管理员变动
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void groupAdmin(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.GROUP_ADMIN);
    }

    /**
     * 群成员减少
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void groupDecrease(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.GROUP_DECREASE);
    }

    /**
     * 群成员增加
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void groupIncrease(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.GROUP_INCREASE);
    }

    /**
     * 群禁言
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void groupBan(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.GROUP_BAN);
    }

    /**
     * 好友添加
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void friendAdd(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.FRIEND_ADD);
    }

    /**
     * 群消息撤回
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void groupMsgDelete(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.GROUP_MSG_DELETE);
    }

    /**
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void privateMsgDelete(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.PRIVATE_MSG_DELETE);
    }

    /**
     * 群成员名片更新
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void groupCardChange(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.GROUP_CARD_CHANGE);
    }

    /**
     * 接收到离线文件
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void offlineFile(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.OFFLINE_FILE);
    }

    /**
     * 子频道创建
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void channelCreated(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.CHANNEL_CREATED);
    }

    /**
     * 子频道删除
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void channelDestroyed(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.CHANNEL_DESTROYED);
    }

    /**
     * 子频道信息更新
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void channelUpdated(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.CHANNEL_UPDATED);
    }

    /**
     * 频道消息表情贴更新
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void messageReactionsUpdated(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.MESSAGE_REACTIONS_UPDATED);
    }

    /**
     * 子通知事件
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void notify(Bot bot, JsonObjectWrapper resp) {
        notify.handler(bot, resp);
    }

    /**
     * 群消息表情贴
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void groupReactionMessage(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.GROUP_MESSAGE_REACTION);
    }

    /**
     * 消息表情回应
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void messageEmojiLikeMessage(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.MESSAGE_EMOJI_LIKE);
    }

    /**
     * 精华消息
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void essenceMessage(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.ESSENCE);
    }

    /**
     * bot 离线
     *
     * @param bot  {@link Bot}
     * @param resp {@link JsonObjectWrapper}
     */
    public void botOffline(Bot bot, JsonObjectWrapper resp) {
        process(bot, resp, NoticeEventEnum.BOT_OFFLINE);
    }
}

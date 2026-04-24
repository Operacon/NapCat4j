package fun.imiku.napcat4j.dispatcher;

import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.notice.BotOfflineEvent;
import com.mikuac.shiro.dto.event.notice.FriendAddNoticeEvent;
import com.mikuac.shiro.dto.event.notice.FriendRecallNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupAdminNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupBanNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupCardNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupDecreaseNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupEssenceNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupGrayTipNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupIncreaseNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupMsgEmojiLikeNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupNameNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupPokeNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupRecallNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupTitleNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupUploadNoticeEvent;
import com.mikuac.shiro.dto.event.notice.InputStatusNoticeEvent;
import com.mikuac.shiro.dto.event.notice.NoticeEvent;
import com.mikuac.shiro.dto.event.notice.ProfileLikeNoticeEvent;
import fun.imiku.napcat4j.annotation.notice.BotOfflineNoticeListener;
import fun.imiku.napcat4j.annotation.notice.FriendAddNoticeListener;
import fun.imiku.napcat4j.annotation.notice.FriendRecallNoticeListener;
import fun.imiku.napcat4j.annotation.notice.GroupAdminNoticeListener;
import fun.imiku.napcat4j.annotation.notice.GroupBanNoticeListener;
import fun.imiku.napcat4j.annotation.notice.GroupCardNoticeListener;
import fun.imiku.napcat4j.annotation.notice.GroupDecreaseNoticeListener;
import fun.imiku.napcat4j.annotation.notice.GroupEssenceNoticeListener;
import fun.imiku.napcat4j.annotation.notice.GroupGrayTipNoticeListener;
import fun.imiku.napcat4j.annotation.notice.GroupIncreaseNoticeListener;
import fun.imiku.napcat4j.annotation.notice.GroupMsgEmojiLikeNoticeListener;
import fun.imiku.napcat4j.annotation.notice.GroupNameNoticeListener;
import fun.imiku.napcat4j.annotation.notice.GroupPokeNoticeListener;
import fun.imiku.napcat4j.annotation.notice.GroupRecallNoticeListener;
import fun.imiku.napcat4j.annotation.notice.GroupTitleNoticeListener;
import fun.imiku.napcat4j.annotation.notice.GroupUploadNoticeListener;
import fun.imiku.napcat4j.annotation.notice.InputStatusNoticeListener;
import fun.imiku.napcat4j.annotation.notice.ProfileLikeNoticeListener;
import fun.imiku.napcat4j.listener.NoticeListener;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.ToIntFunction;

/**
 * Notice 事件分发器。
 * <p>
 * 逻辑与 MessageEventDispatcher 保持一致：
 * 启动时扫描监听器、分发时进行时效校验、异步执行 filter/process。
 */
@Slf4j
@Component
public class NoticeEventDispatcher {

    private final ApplicationContext applicationContext;
    private final ExecutorService virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();

    private List<ListenerBinding<FriendAddNoticeEvent>> friendAddNoticeListeners = List.of();
    private List<ListenerBinding<FriendRecallNoticeEvent>> friendRecallNoticeListeners = List.of();
    private List<ListenerBinding<GroupRecallNoticeEvent>> groupRecallNoticeListeners = List.of();
    private List<ListenerBinding<GroupIncreaseNoticeEvent>> groupIncreaseNoticeListeners = List.of();
    private List<ListenerBinding<GroupDecreaseNoticeEvent>> groupDecreaseNoticeListeners = List.of();
    private List<ListenerBinding<GroupAdminNoticeEvent>> groupAdminNoticeListeners = List.of();
    private List<ListenerBinding<GroupBanNoticeEvent>> groupBanNoticeListeners = List.of();
    private List<ListenerBinding<GroupUploadNoticeEvent>> groupUploadNoticeListeners = List.of();
    private List<ListenerBinding<GroupCardNoticeEvent>> groupCardNoticeListeners = List.of();
    private List<ListenerBinding<GroupNameNoticeEvent>> groupNameNoticeListeners = List.of();
    private List<ListenerBinding<GroupTitleNoticeEvent>> groupTitleNoticeListeners = List.of();
    private List<ListenerBinding<GroupEssenceNoticeEvent>> groupEssenceNoticeListeners = List.of();
    private List<ListenerBinding<GroupGrayTipNoticeEvent>> groupGrayTipNoticeListeners = List.of();
    private List<ListenerBinding<GroupMsgEmojiLikeNoticeEvent>> groupMsgEmojiLikeNoticeListeners = List.of();
    private List<ListenerBinding<GroupPokeNoticeEvent>> groupPokeNoticeListeners = List.of();
    private List<ListenerBinding<ProfileLikeNoticeEvent>> profileLikeNoticeListeners = List.of();
    private List<ListenerBinding<InputStatusNoticeEvent>> inputStatusNoticeListeners = List.of();
    private List<ListenerBinding<BotOfflineEvent>> botOfflineNoticeListeners = List.of();

    public NoticeEventDispatcher(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        this.friendAddNoticeListeners = scanListeners(FriendAddNoticeListener.class, FriendAddNoticeListener::ignoreAfterSec, "@FriendAddNoticeListener");
        this.friendRecallNoticeListeners = scanListeners(FriendRecallNoticeListener.class, FriendRecallNoticeListener::ignoreAfterSec, "@FriendRecallNoticeListener");
        this.groupRecallNoticeListeners = scanListeners(GroupRecallNoticeListener.class, GroupRecallNoticeListener::ignoreAfterSec, "@GroupRecallNoticeListener");
        this.groupIncreaseNoticeListeners = scanListeners(GroupIncreaseNoticeListener.class, GroupIncreaseNoticeListener::ignoreAfterSec, "@GroupIncreaseNoticeListener");
        this.groupDecreaseNoticeListeners = scanListeners(GroupDecreaseNoticeListener.class, GroupDecreaseNoticeListener::ignoreAfterSec, "@GroupDecreaseNoticeListener");
        this.groupAdminNoticeListeners = scanListeners(GroupAdminNoticeListener.class, GroupAdminNoticeListener::ignoreAfterSec, "@GroupAdminNoticeListener");
        this.groupBanNoticeListeners = scanListeners(GroupBanNoticeListener.class, GroupBanNoticeListener::ignoreAfterSec, "@GroupBanNoticeListener");
        this.groupUploadNoticeListeners = scanListeners(GroupUploadNoticeListener.class, GroupUploadNoticeListener::ignoreAfterSec, "@GroupUploadNoticeListener");
        this.groupCardNoticeListeners = scanListeners(GroupCardNoticeListener.class, GroupCardNoticeListener::ignoreAfterSec, "@GroupCardNoticeListener");
        this.groupNameNoticeListeners = scanListeners(GroupNameNoticeListener.class, GroupNameNoticeListener::ignoreAfterSec, "@GroupNameNoticeListener");
        this.groupTitleNoticeListeners = scanListeners(GroupTitleNoticeListener.class, GroupTitleNoticeListener::ignoreAfterSec, "@GroupTitleNoticeListener");
        this.groupEssenceNoticeListeners = scanListeners(GroupEssenceNoticeListener.class, GroupEssenceNoticeListener::ignoreAfterSec, "@GroupEssenceNoticeListener");
        this.groupGrayTipNoticeListeners = scanListeners(GroupGrayTipNoticeListener.class, GroupGrayTipNoticeListener::ignoreAfterSec, "@GroupGrayTipNoticeListener");
        this.groupMsgEmojiLikeNoticeListeners = scanListeners(GroupMsgEmojiLikeNoticeListener.class, GroupMsgEmojiLikeNoticeListener::ignoreAfterSec, "@GroupMsgEmojiLikeNoticeListener");
        this.groupPokeNoticeListeners = scanListeners(GroupPokeNoticeListener.class, GroupPokeNoticeListener::ignoreAfterSec, "@GroupPokeNoticeListener");
        this.profileLikeNoticeListeners = scanListeners(ProfileLikeNoticeListener.class, ProfileLikeNoticeListener::ignoreAfterSec, "@ProfileLikeNoticeListener");
        this.inputStatusNoticeListeners = scanListeners(InputStatusNoticeListener.class, InputStatusNoticeListener::ignoreAfterSec, "@InputStatusNoticeListener");
        this.botOfflineNoticeListeners = scanListeners(BotOfflineNoticeListener.class, BotOfflineNoticeListener::ignoreAfterSec, "@BotOfflineNoticeListener");

        int total = friendAddNoticeListeners.size()
                + friendRecallNoticeListeners.size()
                + groupRecallNoticeListeners.size()
                + groupIncreaseNoticeListeners.size()
                + groupDecreaseNoticeListeners.size()
                + groupAdminNoticeListeners.size()
                + groupBanNoticeListeners.size()
                + groupUploadNoticeListeners.size()
                + groupCardNoticeListeners.size()
                + groupNameNoticeListeners.size()
                + groupTitleNoticeListeners.size()
                + groupEssenceNoticeListeners.size()
                + groupGrayTipNoticeListeners.size()
                + groupMsgEmojiLikeNoticeListeners.size()
                + groupPokeNoticeListeners.size()
                + profileLikeNoticeListeners.size()
                + inputStatusNoticeListeners.size()
                + botOfflineNoticeListeners.size();
        log.info("通知调度器加载完毕，共 {} 个监听器", total);
    }

    @PreDestroy
    public void shutdown() {
        virtualThreadExecutor.close();
    }

    public void dispatch(Bot bot, FriendAddNoticeEvent event) {
        dispatch(bot, event, friendAddNoticeListeners);
    }

    public void dispatch(Bot bot, FriendRecallNoticeEvent event) {
        dispatch(bot, event, friendRecallNoticeListeners);
    }

    public void dispatch(Bot bot, GroupRecallNoticeEvent event) {
        dispatch(bot, event, groupRecallNoticeListeners);
    }

    public void dispatch(Bot bot, GroupIncreaseNoticeEvent event) {
        dispatch(bot, event, groupIncreaseNoticeListeners);
    }

    public void dispatch(Bot bot, GroupDecreaseNoticeEvent event) {
        dispatch(bot, event, groupDecreaseNoticeListeners);
    }

    public void dispatch(Bot bot, GroupAdminNoticeEvent event) {
        dispatch(bot, event, groupAdminNoticeListeners);
    }

    public void dispatch(Bot bot, GroupBanNoticeEvent event) {
        dispatch(bot, event, groupBanNoticeListeners);
    }

    public void dispatch(Bot bot, GroupUploadNoticeEvent event) {
        dispatch(bot, event, groupUploadNoticeListeners);
    }

    public void dispatch(Bot bot, GroupCardNoticeEvent event) {
        dispatch(bot, event, groupCardNoticeListeners);
    }

    public void dispatch(Bot bot, GroupNameNoticeEvent event) {
        dispatch(bot, event, groupNameNoticeListeners);
    }

    public void dispatch(Bot bot, GroupTitleNoticeEvent event) {
        dispatch(bot, event, groupTitleNoticeListeners);
    }

    public void dispatch(Bot bot, GroupEssenceNoticeEvent event) {
        dispatch(bot, event, groupEssenceNoticeListeners);
    }

    public void dispatch(Bot bot, GroupGrayTipNoticeEvent event) {
        dispatch(bot, event, groupGrayTipNoticeListeners);
    }

    public void dispatch(Bot bot, GroupMsgEmojiLikeNoticeEvent event) {
        dispatch(bot, event, groupMsgEmojiLikeNoticeListeners);
    }

    public void dispatch(Bot bot, GroupPokeNoticeEvent event) {
        dispatch(bot, event, groupPokeNoticeListeners);
    }

    public void dispatch(Bot bot, ProfileLikeNoticeEvent event) {
        dispatch(bot, event, profileLikeNoticeListeners);
    }

    public void dispatch(Bot bot, InputStatusNoticeEvent event) {
        dispatch(bot, event, inputStatusNoticeListeners);
    }

    public void dispatch(Bot bot, BotOfflineEvent event) {
        dispatch(bot, event, botOfflineNoticeListeners);
    }

    private <T extends NoticeEvent> void dispatch(Bot bot, T event, List<ListenerBinding<T>> listeners) {
        if (listeners.isEmpty()) {
            return;
        }
        long now = Instant.now().getEpochSecond();
        for (ListenerBinding<T> binding : listeners) {
            dispatchAsync(bot, event, binding, now);
        }
    }

    private <T extends NoticeEvent> void dispatchAsync(Bot bot, T event, ListenerBinding<T> binding, long now) {
        if (!shouldProcessByTime(now, event, binding.ignoreSeconds())) {
            return;
        }
        virtualThreadExecutor.execute(() -> {
            try {
                T filteredEvent = binding.listener().filter(bot, event);
                if (filteredEvent == null) {
                    return;
                }
                binding.listener().process(bot, filteredEvent);
            } catch (Exception e) {
                log.error("消费通知事件失败 listener={}, noticeType={}",
                        binding.listener().getClass().getName(), event.getNoticeType(), e);
            }
        });
    }

    private boolean shouldProcessByTime(long now, NoticeEvent event, int ignoreSeconds) {
        if (ignoreSeconds == -1) {
            return true;
        }
        Long eventTime = event.getTime();
        if (eventTime == null) {
            return false;
        }
        return Math.abs(now - eventTime) <= ignoreSeconds;
    }

    @SuppressWarnings("unchecked")
    private <A extends Annotation, T extends NoticeEvent> List<ListenerBinding<T>> scanListeners(
            Class<A> annotationClass,
            ToIntFunction<A> ignoreSecondsProvider,
            String annotationName
    ) {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(annotationClass);
        List<ListenerBinding<T>> bindings = new ArrayList<>(beans.size());
        for (Object bean : beans.values()) {
            A annotation = AnnotationUtils.findAnnotation(AopUtils.getTargetClass(bean), annotationClass);
            if (annotation == null) {
                continue;
            }
            if (!(bean instanceof NoticeListener<?> listener)) {
                log.error("{} 标注的 Bean 未实现 NoticeListener: {}", annotationName, bean.getClass().getName());
                continue;
            }
            bindings.add(new ListenerBinding<>((NoticeListener<T>) listener, ignoreSecondsProvider.applyAsInt(annotation)));
        }
        return List.copyOf(bindings);
    }

    private record ListenerBinding<T extends NoticeEvent>(NoticeListener<T> listener, int ignoreSeconds) {
    }
}

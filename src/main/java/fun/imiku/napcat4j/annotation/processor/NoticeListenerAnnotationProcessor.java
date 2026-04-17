package fun.imiku.napcat4j.annotation.processor;

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

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.lang.annotation.Annotation;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

/**
 * 校验 Notice 监听注解的使用方式：
 * <ul>
 *   <li>注解仅允许用于类</li>
 *   <li>类必须实现 {@code NoticeListener<T extends NoticeEvent>}</li>
 *   <li>泛型参数必须与注解要求的事件类型一致</li>
 * </ul>
 */
@SupportedAnnotationTypes({
        "fun.imiku.napcat4j.annotation.notice.FriendAddNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.FriendRecallNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.GroupRecallNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.GroupIncreaseNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.GroupDecreaseNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.GroupAdminNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.GroupBanNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.GroupUploadNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.GroupCardNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.GroupNameNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.GroupTitleNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.GroupEssenceNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.GroupGrayTipNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.GroupMsgEmojiLikeNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.GroupPokeNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.ProfileLikeNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.InputStatusNoticeListener",
        "fun.imiku.napcat4j.annotation.notice.BotOfflineNoticeListener"
})
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class NoticeListenerAnnotationProcessor extends AbstractProcessor {

    private static final String NOTICE_LISTENER_FQCN = "fun.imiku.napcat4j.listener.NoticeListener";

    private static final String FRIEND_ADD_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.FriendAddNoticeEvent";
    private static final String FRIEND_RECALL_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.FriendRecallNoticeEvent";
    private static final String GROUP_RECALL_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.GroupRecallNoticeEvent";
    private static final String GROUP_INCREASE_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.GroupIncreaseNoticeEvent";
    private static final String GROUP_DECREASE_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.GroupDecreaseNoticeEvent";
    private static final String GROUP_ADMIN_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.GroupAdminNoticeEvent";
    private static final String GROUP_BAN_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.GroupBanNoticeEvent";
    private static final String GROUP_UPLOAD_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.GroupUploadNoticeEvent";
    private static final String GROUP_CARD_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.GroupCardNoticeEvent";
    private static final String GROUP_NAME_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.GroupNameNoticeEvent";
    private static final String GROUP_TITLE_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.GroupTitleNoticeEvent";
    private static final String GROUP_ESSENCE_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.GroupEssenceNoticeEvent";
    private static final String GROUP_GRAY_TIP_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.GroupGrayTipNoticeEvent";
    private static final String GROUP_MSG_EMOJI_LIKE_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.GroupMsgEmojiLikeNoticeEvent";
    private static final String GROUP_POKE_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.GroupPokeNoticeEvent";
    private static final String PROFILE_LIKE_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.ProfileLikeNoticeEvent";
    private static final String INPUT_STATUS_NOTICE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.InputStatusNoticeEvent";
    private static final String BOT_OFFLINE_EVENT_FQCN = "com.mikuac.shiro.dto.event.notice.BotOfflineEvent";

    private Types typeUtils;
    private Elements elementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.typeUtils = processingEnv.getTypeUtils();
        this.elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        validateAnnotatedTypes(roundEnv, FriendAddNoticeListener.class, FRIEND_ADD_NOTICE_EVENT_FQCN, "@FriendAddNoticeListener");
        validateAnnotatedTypes(roundEnv, FriendRecallNoticeListener.class, FRIEND_RECALL_NOTICE_EVENT_FQCN, "@FriendRecallNoticeListener");
        validateAnnotatedTypes(roundEnv, GroupRecallNoticeListener.class, GROUP_RECALL_NOTICE_EVENT_FQCN, "@GroupRecallNoticeListener");
        validateAnnotatedTypes(roundEnv, GroupIncreaseNoticeListener.class, GROUP_INCREASE_NOTICE_EVENT_FQCN, "@GroupIncreaseNoticeListener");
        validateAnnotatedTypes(roundEnv, GroupDecreaseNoticeListener.class, GROUP_DECREASE_NOTICE_EVENT_FQCN, "@GroupDecreaseNoticeListener");
        validateAnnotatedTypes(roundEnv, GroupAdminNoticeListener.class, GROUP_ADMIN_NOTICE_EVENT_FQCN, "@GroupAdminNoticeListener");
        validateAnnotatedTypes(roundEnv, GroupBanNoticeListener.class, GROUP_BAN_NOTICE_EVENT_FQCN, "@GroupBanNoticeListener");
        validateAnnotatedTypes(roundEnv, GroupUploadNoticeListener.class, GROUP_UPLOAD_NOTICE_EVENT_FQCN, "@GroupUploadNoticeListener");
        validateAnnotatedTypes(roundEnv, GroupCardNoticeListener.class, GROUP_CARD_NOTICE_EVENT_FQCN, "@GroupCardNoticeListener");
        validateAnnotatedTypes(roundEnv, GroupNameNoticeListener.class, GROUP_NAME_NOTICE_EVENT_FQCN, "@GroupNameNoticeListener");
        validateAnnotatedTypes(roundEnv, GroupTitleNoticeListener.class, GROUP_TITLE_NOTICE_EVENT_FQCN, "@GroupTitleNoticeListener");
        validateAnnotatedTypes(roundEnv, GroupEssenceNoticeListener.class, GROUP_ESSENCE_NOTICE_EVENT_FQCN, "@GroupEssenceNoticeListener");
        validateAnnotatedTypes(roundEnv, GroupGrayTipNoticeListener.class, GROUP_GRAY_TIP_NOTICE_EVENT_FQCN, "@GroupGrayTipNoticeListener");
        validateAnnotatedTypes(roundEnv, GroupMsgEmojiLikeNoticeListener.class, GROUP_MSG_EMOJI_LIKE_NOTICE_EVENT_FQCN, "@GroupMsgEmojiLikeNoticeListener");
        validateAnnotatedTypes(roundEnv, GroupPokeNoticeListener.class, GROUP_POKE_NOTICE_EVENT_FQCN, "@GroupPokeNoticeListener");
        validateAnnotatedTypes(roundEnv, ProfileLikeNoticeListener.class, PROFILE_LIKE_NOTICE_EVENT_FQCN, "@ProfileLikeNoticeListener");
        validateAnnotatedTypes(roundEnv, InputStatusNoticeListener.class, INPUT_STATUS_NOTICE_EVENT_FQCN, "@InputStatusNoticeListener");
        validateAnnotatedTypes(roundEnv, BotOfflineNoticeListener.class, BOT_OFFLINE_EVENT_FQCN, "@BotOfflineNoticeListener");
        return false;
    }

    private void validateAnnotatedTypes(
            RoundEnvironment roundEnv,
            Class<? extends Annotation> annotationType,
            String expectedEventTypeFqcn,
            String annotationName
    ) {
        TypeElement noticeListenerType = elementUtils.getTypeElement(NOTICE_LISTENER_FQCN);
        TypeElement expectedEventType = elementUtils.getTypeElement(expectedEventTypeFqcn);
        if (noticeListenerType == null || expectedEventType == null) {
            return;
        }

        TypeMirror noticeListenerErasure = typeUtils.erasure(noticeListenerType.asType());
        TypeMirror expectedEventErasure = typeUtils.erasure(expectedEventType.asType());

        for (Element element : roundEnv.getElementsAnnotatedWith(annotationType)) {
            if (element.getKind() != ElementKind.CLASS) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        annotationName + " 只能标注在类上。",
                        element
                );
                continue;
            }

            TypeElement classElement = (TypeElement) element;
            Optional<TypeMirror> listenerArg = findNoticeListenerTypeArgument(classElement.asType(), noticeListenerErasure);
            if (listenerArg.isEmpty()) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        annotationName + " 要求实现 NoticeListener<T extends NoticeEvent>。",
                        classElement
                );
                continue;
            }

            TypeMirror actualArgErasure = typeUtils.erasure(listenerArg.get());
            if (!typeUtils.isSameType(actualArgErasure, expectedEventErasure)) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        annotationName + " 要求实现 NoticeListener<" + expectedEventTypeFqcn + ">。",
                        classElement
                );
            }
        }
    }

    private Optional<TypeMirror> findNoticeListenerTypeArgument(TypeMirror root, TypeMirror noticeListenerErasure) {
        Queue<TypeMirror> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TypeMirror current = queue.poll();
            if (current == null || !visited.add(current.toString())) {
                continue;
            }
            if (!(current instanceof DeclaredType declaredType)) {
                continue;
            }

            if (typeUtils.isSameType(typeUtils.erasure(declaredType), noticeListenerErasure)) {
                if (!declaredType.getTypeArguments().isEmpty()) {
                    return Optional.of(declaredType.getTypeArguments().getFirst());
                }
                return Optional.empty();
            }

            for (TypeMirror superType : typeUtils.directSupertypes(current)) {
                queue.add(superType);
            }
        }

        return Optional.empty();
    }
}

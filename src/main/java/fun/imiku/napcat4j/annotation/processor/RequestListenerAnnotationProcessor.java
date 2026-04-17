package fun.imiku.napcat4j.annotation.processor;

import fun.imiku.napcat4j.annotation.FriendRequestListener;
import fun.imiku.napcat4j.annotation.GroupRequestListener;

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
 * Validates listener annotation usage:
 * <ul>
 *   <li>{@link FriendRequestListener}: class must implement {@code RequestListener<FriendAddRequestEvent>}</li>
 *   <li>{@link GroupRequestListener}: class must implement {@code RequestListener<GroupAddRequestEvent>}</li>
 * </ul>
 */
@SupportedAnnotationTypes({
        "fun.imiku.napcat4j.annotation.FriendRequestListener",
        "fun.imiku.napcat4j.annotation.GroupRequestListener"
})
@SupportedSourceVersion(SourceVersion.RELEASE_25)
public class RequestListenerAnnotationProcessor extends AbstractProcessor {

    private static final String REQUEST_LISTENER_FQCN = "fun.imiku.napcat4j.listener.RequestListener";
    private static final String FRIEND_EVENT_FQCN = "com.mikuac.shiro.dto.event.request.FriendAddRequestEvent";
    private static final String GROUP_EVENT_FQCN = "com.mikuac.shiro.dto.event.request.GroupAddRequestEvent";

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
        validateAnnotatedTypes(roundEnv, FriendRequestListener.class, FRIEND_EVENT_FQCN, "@FriendRequestListener");
        validateAnnotatedTypes(roundEnv, GroupRequestListener.class, GROUP_EVENT_FQCN, "@GroupRequestListener");
        return false;
    }

    private void validateAnnotatedTypes(
            RoundEnvironment roundEnv,
            Class<? extends Annotation> annotationType,
            String expectedEventTypeFqcn,
            String annotationName
    ) {
        TypeElement requestListenerType = elementUtils.getTypeElement(REQUEST_LISTENER_FQCN);
        TypeElement expectedEventType = elementUtils.getTypeElement(expectedEventTypeFqcn);
        if (requestListenerType == null || expectedEventType == null) {
            return;
        }

        TypeMirror requestListenerErasure = typeUtils.erasure(requestListenerType.asType());
        TypeMirror expectedEventErasure = typeUtils.erasure(expectedEventType.asType());

        for (Element element : roundEnv.getElementsAnnotatedWith(annotationType)) {
            if (element.getKind() != ElementKind.CLASS) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        annotationName + " can only be applied to classes.",
                        element
                );
                continue;
            }

            TypeElement classElement = (TypeElement) element;
            Optional<TypeMirror> listenerArg = findRequestListenerTypeArgument(classElement.asType(), requestListenerErasure);
            if (listenerArg.isEmpty()) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        annotationName + " requires implementing RequestListener<T extends RequestEvent>.",
                        classElement
                );
                continue;
            }

            TypeMirror actualArgErasure = typeUtils.erasure(listenerArg.get());
            if (!typeUtils.isSameType(actualArgErasure, expectedEventErasure)) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        annotationName + " requires RequestListener<" + expectedEventTypeFqcn + ">.",
                        classElement
                );
            }
        }
    }

    private Optional<TypeMirror> findRequestListenerTypeArgument(TypeMirror root, TypeMirror requestListenerErasure) {
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

            if (typeUtils.isSameType(typeUtils.erasure(declaredType), requestListenerErasure)) {
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

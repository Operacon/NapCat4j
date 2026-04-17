package fun.imiku.napcat4j.annotation.processor;

import fun.imiku.napcat4j.annotation.meta.HeartbeatListener;
import fun.imiku.napcat4j.annotation.meta.LifeCycleListener;

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
 *   <li>{@link HeartbeatListener}: class must implement {@code MetaListener<HeartbeatMetaEvent>}</li>
 *   <li>{@link LifeCycleListener}: class must implement {@code MetaListener<LifecycleMetaEvent>}</li>
 * </ul>
 */
@SupportedAnnotationTypes({
        "fun.imiku.napcat4j.annotation.meta.HeartbeatListener",
        "fun.imiku.napcat4j.annotation.meta.LifeCycleListener"
})
@SupportedSourceVersion(SourceVersion.RELEASE_25)
public class MetaListenerAnnotationProcessor extends AbstractProcessor {

    private static final String META_LISTENER_FQCN = "fun.imiku.napcat4j.listener.MetaListener";
    private static final String HEARTBEAT_EVENT_FQCN = "com.mikuac.shiro.dto.event.meta.HeartbeatMetaEvent";
    private static final String LIFECYCLE_EVENT_FQCN = "com.mikuac.shiro.dto.event.meta.LifecycleMetaEvent";

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
        validateAnnotatedTypes(roundEnv, HeartbeatListener.class, HEARTBEAT_EVENT_FQCN, "@HeartbeatListener");
        validateAnnotatedTypes(roundEnv, LifeCycleListener.class, LIFECYCLE_EVENT_FQCN, "@LifeCycleListener");
        return false;
    }

    private void validateAnnotatedTypes(
            RoundEnvironment roundEnv,
            Class<? extends Annotation> annotationType,
            String expectedEventTypeFqcn,
            String annotationName
    ) {
        TypeElement metaListenerType = elementUtils.getTypeElement(META_LISTENER_FQCN);
        TypeElement expectedEventType = elementUtils.getTypeElement(expectedEventTypeFqcn);
        if (metaListenerType == null || expectedEventType == null) {
            return;
        }

        TypeMirror metaListenerErasure = typeUtils.erasure(metaListenerType.asType());
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
            Optional<TypeMirror> listenerArg = findMetaListenerTypeArgument(classElement.asType(), metaListenerErasure);
            if (listenerArg.isEmpty()) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        annotationName + " requires implementing MetaListener<T extends MetaEvent>.",
                        classElement
                );
                continue;
            }

            TypeMirror actualArgErasure = typeUtils.erasure(listenerArg.get());
            if (!typeUtils.isSameType(actualArgErasure, expectedEventErasure)) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        annotationName + " requires MetaListener<" + expectedEventTypeFqcn + ">.",
                        classElement
                );
            }
        }
    }

    private Optional<TypeMirror> findMetaListenerTypeArgument(TypeMirror root, TypeMirror metaListenerErasure) {
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

            if (typeUtils.isSameType(typeUtils.erasure(declaredType), metaListenerErasure)) {
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


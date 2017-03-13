package com.leonhart.actorzilla.invoke.reflectasm;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.leonhart.actorzilla.core.Actor;
import com.leonhart.actorzilla.core.MessageHandler;

import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by david on 11.03.2017.
 */
public class ReflectAsmActorMetadata {
    private static final Map<Class, ReflectAsmActorMetadata> metadataCacheByActor = new ConcurrentHashMap<>(256);

    private final Map<Class, InvocableMethod> methodCache;

    public ReflectAsmActorMetadata(final Class<? extends Actor> actorClazz) {
        this.methodCache = Arrays.stream(actorClazz.getMethods())
                .filter(method -> method.isAnnotationPresent(MessageHandler.class) && method.getParameterCount() == 2)
                .map(method -> new AbstractMap.SimpleEntry<>(method.getParameterTypes()[1], createReflectAsmMethod(actorClazz, method)))
                .collect(Collectors.toMap(x -> x.getKey(), y -> y.getValue()));
    }

    private ReflectAsmMethod createReflectAsmMethod(final Class<? extends Actor> actorClazz, final Method method) {
        final MethodAccess methodAccess = MethodAccess.get(actorClazz);
        final int index = methodAccess.getIndex(method.getName(), method.getParameterTypes());
        return new ReflectAsmMethod(methodAccess, index);
    }

    public Optional<InvocableMethod> findMethod(final Object message) {
        return Optional.ofNullable(this.methodCache.get(message.getClass()));
    }

    public static ReflectAsmActorMetadata forActor(final ReflectiveActor reflectiveActor) {
        return metadataCacheByActor.computeIfAbsent(reflectiveActor.getClass(), ReflectAsmActorMetadata::new);
    }
}

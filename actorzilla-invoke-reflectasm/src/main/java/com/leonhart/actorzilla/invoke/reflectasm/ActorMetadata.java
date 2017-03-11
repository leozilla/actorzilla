package com.leonhart.actorzilla.invoke.reflectasm;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.leonhart.actorzilla.core.Actor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by david on 11.03.2017.
 */
public class ActorMetadata {
    private final Map<Object, InvokableMethod> methodCache = new HashMap<>(8);
    private final Actor actor;

    public ActorMetadata(final Actor actor) {
        this.actor = actor;
    }

    public Optional<InvokableMethod> findMethod(final Object message) {
        return Optional.ofNullable(this.methodCache.computeIfAbsent(message, msg -> {
            final Class<? extends Actor> actorClazz = this.actor.getClass();
            final Method met = Arrays.stream(actorClazz.getMethods())
                    .filter(method -> method.isAnnotationPresent(EventHandler.class))
                    .filter(method -> method.getParameterCount() >= 2 && method.getParameterTypes()[1].equals(msg.getClass()))
                    .findFirst()
                    .get(); // TODO

            final MethodAccess methodAccess = MethodAccess.get(actorClazz);
            final int index = methodAccess.getIndex(met.getName(), met.getParameterTypes());
            return new ReflectAsmMethod(methodAccess, index);
        }));
    }
}

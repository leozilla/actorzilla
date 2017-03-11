package com.leonhart.actorzilla.invoke.reflectasm;

import com.leonhart.actorzilla.core.Actor;
import com.leonhart.actorzilla.core.Invoker;
import com.leonhart.actorzilla.core.MessageContext;

import java.util.Optional;

/**
 * Created by david on 11.03.2017.
 */
public class ReflectAsmInvoker implements Invoker {
    private final ActorMetadata metadata;

    public ReflectAsmInvoker(final Actor innerActor) {
        this(new ActorMetadata(innerActor));
    }

    public ReflectAsmInvoker(final ActorMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public void invoke(final Actor actorTarget, final MessageContext messageContext, final Object message) {
        final Optional<InvocableMethod> method = this.metadata.findMethod(message);
        if (method.isPresent()) {
            method.get().invoke(actorTarget, messageContext, message);
        } else {
            // TODO
        }
    }

    ActorMetadata getMetadata() {
        return this.metadata;
    }
}

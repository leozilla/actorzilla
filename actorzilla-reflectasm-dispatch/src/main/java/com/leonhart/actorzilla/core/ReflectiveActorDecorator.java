package com.leonhart.actorzilla.core;

import java.util.Optional;

/**
 * Created by david on 11.03.2017.
 */
public class ReflectiveActorDecorator extends Actor {
    private final Actor innerActor;
    private final ActorMetadata metadata;

    public ReflectiveActorDecorator(final Actor innerActor) {
        this(innerActor, new ActorMetadata(innerActor));
    }

    public ReflectiveActorDecorator(final Actor innerActor, final ActorMetadata metadata) {
        this.innerActor = innerActor;
        this.metadata = metadata;
    }

    @Override
    public void receive(final MessageContext messageContext, final Object message) {
        final Optional<InvokableMethod> method = this.metadata.findMethod(message);
        if (method.isPresent()) {
            method.get().invoke(this.innerActor, message);
        } else {
            // TODO
        }
    }

    @Override
    void setSelf(final ActorRef self) {
        this.innerActor.setSelf(self);
    }

    @Override
    void setSender(final ActorRef sender) {
        this.innerActor.setSender(sender);
    }

    Actor getInnerActor() {
        return this.innerActor;
    }

    ActorMetadata getMetadata() {
        return this.metadata;
    }
}

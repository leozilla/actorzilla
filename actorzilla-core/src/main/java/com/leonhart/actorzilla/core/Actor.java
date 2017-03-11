package com.leonhart.actorzilla.core;

/**
 * Created by david on 11.03.2017.
 */
public abstract class Actor {

    private ActorRef self;

    protected ActorRef getSelf() {
        return this.self;
    }

    void receiveMessage(final MessageContext ctx, final Object message) {
        receive(ctx, message);
    }

    protected abstract void receive(MessageContext ctx, Object message);

    void setSelf(final ActorRef self) {
        this.self = self;
    }
}

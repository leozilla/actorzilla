package com.leonhart.actorzilla.core;

/**
 * Created by david on 11.03.2017.
 */
public final class MessageEnvelope {
    private final Object message;
    private final MessageContext ctx;

    public MessageEnvelope(final MessageContext ctx, final Object message) {
        this.ctx = ctx;
        this.message = message;
    }

    public ActorRef getSender() {
        return this.ctx.getSender();
    }

    public Object getMessage() {
        return this.message;
    }

    public MessageContext getContext() {
        return this.ctx;
    }
}

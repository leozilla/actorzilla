package com.leonhart.actorzilla.core;

/**
 * Created by david on 11.03.2017.
 */
public abstract class Actor {

    private ActorRef sender;
    private ActorRef self;

    protected ActorRef getSelf() {
        return this.self;
    }

    protected ActorRef getSender() {
        return this.sender;
    }

    void receiveMessage(final MessageContext ctx, final MessageEnvelope envelope) {
        setSender(envelope.getSender());
        receive(ctx, envelope.getMessage());
    }

    protected abstract void receive(MessageContext ctx, Object message);

    void setSender(final ActorRef sender) {
        this.sender = sender;
    }

    void setSelf(final ActorRef self) {
        this.self = self;
    }
}

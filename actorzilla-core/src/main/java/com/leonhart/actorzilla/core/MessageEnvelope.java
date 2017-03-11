package com.leonhart.actorzilla.core;

/**
 * Created by david on 11.03.2017.
 */
public final class MessageEnvelope {
    private final ActorRef sender;
    private final Object message;

    public MessageEnvelope(final ActorRef sender, final Object message) {

        this.sender = sender;
        this.message = message;
    }

    public ActorRef getSender() {
        return this.sender;
    }

    public Object getMessage() {
        return this.message;
    }
}

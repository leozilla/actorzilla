package com.leonhart.actorzilla.core;

/**
 * Created by david on 11.03.2017.
 */
public class MessageContextImpl implements MessageContext {
    private final ActorRef sender;

    public MessageContextImpl(final ActorRef sender) {
        this.sender = sender;
    }

    @Override
    public ActorRef getSender() {
        return this.sender;
    }
}

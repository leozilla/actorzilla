package com.leonhart.actorzilla.core;

/**
 * Created by david on 11.03.2017.
 */
public abstract class Actor {

    private ActorRef sender;
    private ActorRef self;

    ActorRef getSelf() {
        return this.self;
    }

    ActorRef getSender() {
        return this.sender;
    }

    public abstract void receive(MessageContext ctx, Object message);

    void setSender(final ActorRef sender) {
        this.sender = sender;
    }

    void setSelf(final ActorRef self) {
        this.self = self;
    }
}

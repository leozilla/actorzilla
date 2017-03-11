package com.leonhart.actorzilla.core;

/**
 * Created by david on 11.03.2017.
 */
public abstract class AbstractMessageContext implements MessageContext {

    @Override
    public ActorRef getSender() {
        return null;
    }
}

package com.leonhart.actorzilla.core;

/**
 * Created by david on 11.03.2017.
 */
public interface Dispatcher {

    void dispatch(MessageEnvelope message, ActorShell receiver);
}

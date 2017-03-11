package com.leonhart.actorzilla.core;

import com.leonhart.actorzilla.core.dispatch.MessageQueue;

/**
 * Created by david on 11.03.2017.
 */
public interface Dispatcher {

    void dispatch(MessageEnvelope message, ActorShell receiver);

    default Mailbox createMailbox(final Invoker invoker, final MessageQueue messageQueue) {
        return new Mailbox(invoker, messageQueue);
    }
}

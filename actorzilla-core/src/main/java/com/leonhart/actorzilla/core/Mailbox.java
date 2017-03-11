package com.leonhart.actorzilla.core;

import com.leonhart.actorzilla.core.dispatch.MessageQueue;

/**
 * Created by david on 11.03.2017.
 */
public class Mailbox implements Runnable {
    private final Invoker invoker;
    private final MessageQueue messageQueue;
    private Actor actor;

    protected Mailbox(final Invoker invoker, final MessageQueue messageQueue) {
        this.invoker = invoker;
        this.messageQueue = messageQueue;
    }

    void enqueue(final MessageEnvelope message) {
        this.messageQueue.enqueue(message);
    }

    void setActor(final Actor actor) {
        this.actor = actor;
    }

    protected MessageEnvelope dequeue() {
        return this.messageQueue.dequeue();
    }

    @Override
    public void run() {
        final MessageEnvelope envelope = dequeue();
        this.invoker.invoke(this.actor, envelope.getContext(), envelope.getMessage());
    }
}

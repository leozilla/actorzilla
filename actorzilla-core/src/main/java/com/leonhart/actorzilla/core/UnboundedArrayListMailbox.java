package com.leonhart.actorzilla.core;

import java.util.ArrayList;

/**
 * Created by david on 11.03.2017.
 */
public class UnboundedArrayListMailbox implements Mailbox {
    private final ArrayList<MessageEnvelope> messages = new ArrayList<>();
    private final Actor actor;
    private final Invoker invoker;

    public UnboundedArrayListMailbox(final Actor actor, final Invoker invoker) {
        this.actor = actor;
        this.invoker = invoker;
    }

    @Override
    public void enqueue(final MessageEnvelope message) {
        this.messages.add(message);
    }


    @Override
    public void run() {
        for (final MessageEnvelope envelope : this.messages) {
            this.invoker.invoke(this.actor, envelope.getContext(), envelope.getMessage());
        }
    }
}

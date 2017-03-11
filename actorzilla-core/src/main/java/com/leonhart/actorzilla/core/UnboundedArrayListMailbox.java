package com.leonhart.actorzilla.core;

import java.util.ArrayList;

/**
 * Created by david on 11.03.2017.
 */
public class UnboundedArrayListMailbox implements Mailbox {
    private final ArrayList<MessageEnvelope> messages = new ArrayList<>();
    private final Actor actor;

    public UnboundedArrayListMailbox(final Actor actor) {
        this.actor = actor;
    }

    @Override
    public void enqueue(final MessageEnvelope message) {
        this.messages.add(message);
    }


    @Override
    public void run() {
        for (final MessageEnvelope envelope : this.messages) {
            this.actor.setSender(envelope.getSender());
            this.actor.receive(null, envelope.getMessage());
        }
    }
}

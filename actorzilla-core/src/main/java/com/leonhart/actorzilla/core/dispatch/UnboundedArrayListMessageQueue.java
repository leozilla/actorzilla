package com.leonhart.actorzilla.core.dispatch;

import com.leonhart.actorzilla.core.MessageEnvelope;

import java.util.ArrayList;

/**
 * Created by david on 11.03.2017.
 */
public class UnboundedArrayListMessageQueue implements MessageQueue {
    private final ArrayList<MessageEnvelope> messages = new ArrayList<>();

    @Override
    public void enqueue(final MessageEnvelope message) {
        this.messages.add(message);
    }

    @Override
    public MessageEnvelope dequeue() {
        return !this.messages.isEmpty() ? this.messages.remove(0) : null;
    }
}

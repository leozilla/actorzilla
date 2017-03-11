package com.leonhart.actorzilla.core.dispatch;

import com.leonhart.actorzilla.core.MessageEnvelope;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by david on 11.03.2017.
 */
public class ConcurrentLinkedMessageQueue implements MessageQueue {
    private final ConcurrentLinkedQueue<MessageEnvelope> messages = new ConcurrentLinkedQueue<MessageEnvelope>();

    @Override
    public MessageEnvelope dequeue() {
        return this.messages.peek();
    }

    @Override
    public void enqueue(final MessageEnvelope message) {
        this.messages.offer(message);
    }
}

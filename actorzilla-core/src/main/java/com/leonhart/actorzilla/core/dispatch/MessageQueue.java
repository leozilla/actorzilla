package com.leonhart.actorzilla.core.dispatch;

import com.leonhart.actorzilla.core.MessageEnvelope;

/**
 * Created by david on 11.03.2017.
 */
public interface MessageQueue {
    MessageEnvelope dequeue();

    void enqueue(MessageEnvelope message);
}

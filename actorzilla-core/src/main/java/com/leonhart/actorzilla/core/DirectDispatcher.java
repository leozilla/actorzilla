package com.leonhart.actorzilla.core;

import java.util.concurrent.ExecutorService;

/**
 * Created by david on 11.03.2017.
 */
public class DirectDispatcher implements Dispatcher {
    private final ExecutorService executor;

    public DirectDispatcher(final ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public void dispatch(final MessageEnvelope message, final ActorShell receiver) {
        final Mailbox mbox = receiver.getMailbox();
        mbox.enqueue(message);
        this.executor.submit(mbox);
    }
}

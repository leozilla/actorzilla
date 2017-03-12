package com.leonhart.actorzilla.core;

import java.util.concurrent.CompletableFuture;

/**
 * Created by david on 11.03.2017.
 */
public abstract class ActorRef {
    public static final ActorRef NO_SENDER = null; // TODO

    private final ActorShell shell;

    protected ActorRef(final ActorShell shell) {
        this.shell = shell;
    }

    public abstract void send(final Object message, final ActorRef sender);

    public CompletableFuture<MessageEnvelope> ask(final Object message) {
        return ask(message, ActorRef.NO_SENDER);
    }

    public abstract CompletableFuture<MessageEnvelope> ask(final Object message, final ActorRef sender);

    ActorShell getShell() {
        return this.shell;
    }
}

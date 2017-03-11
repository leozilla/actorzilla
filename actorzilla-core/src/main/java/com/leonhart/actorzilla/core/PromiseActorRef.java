package com.leonhart.actorzilla.core;

import java.util.concurrent.CompletableFuture;

/**
 * Created by david on 11.03.2017.
 */
public class PromiseActorRef extends ActorRef {
    private final CompletableFuture<Object> promise;

    protected PromiseActorRef(final CompletableFuture<Object> promise) {
        super(null); // TODO
        this.promise = promise;
    }

    @Override
    public void send(final Object message, final ActorRef sender) {
        this.promise.complete(message);
    }

    @Override
    public CompletableFuture<Object> ask(final Object message, final ActorRef sender) {
        return null;
    }
}

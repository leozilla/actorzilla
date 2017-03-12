package com.leonhart.actorzilla.core;

import com.leonhart.actorzilla.core.dispatch.MessageQueue;

import java.util.concurrent.CompletableFuture;

/**
 * Created by david on 11.03.2017.
 */
public class LocalActorRef extends ActorRef {
    private final Actor actor;

    public LocalActorRef(final Dispatcher dispatcher, final Invoker invoker, final MessageQueue messageQueue, final Actor actor) {
        super(new ActorShell(dispatcher, dispatcher.createMailbox(invoker, messageQueue), actor));
        this.actor = actor;

        actor.setSelf(this);
    }

    @Override
    public void send(final Object message, final ActorRef sender) {
        getShell().send(message, sender);
    }

    @Override
    public CompletableFuture<MessageEnvelope> ask(final Object message, final ActorRef sender) {
        final CompletableFuture<MessageEnvelope> promise = new CompletableFuture<>();
        getShell().send(message, new PromiseActorRef(promise));
        return promise;
    }


    @Override
    public String toString() {
        return this.actor.toString();
    }

    protected Actor getActor() {
        return this.actor;
    }
}

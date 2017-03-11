package com.leonhart.actorzilla.core;

/**
 * Created by david on 11.03.2017.
 */
public class LocalActorRef extends ActorRef {
    private final Dispatcher dispatcher;
    private final Actor actor;

    public LocalActorRef(final Dispatcher dispatcher, final Actor actor) {
        super(new ActorShell(new UnboundedArrayListMailbox(actor), actor.toString()));
        this.dispatcher = dispatcher;
        this.actor = actor;

        actor.setSelf(this);
    }

    @Override
    public void send(final Object message, final ActorRef sender) {
        this.dispatcher.dispatch(new MessageEnvelope(sender, message), getShell());
    }


    @Override
    public String toString() {
        return this.actor.toString();
    }

    Actor getActor() {
        return this.actor;
    }
}

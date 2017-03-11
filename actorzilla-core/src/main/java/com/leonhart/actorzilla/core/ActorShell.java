package com.leonhart.actorzilla.core;

/**
 * Created by david on 11.03.2017.
 */
class ActorShell {
    private final Dispatcher dispatcher;
    private final Mailbox mailbox;
    private final Actor actor;

    ActorShell(final Dispatcher dispatcher, final Mailbox mailbox, final Actor actor) {
        this.dispatcher = dispatcher;
        this.mailbox = mailbox;
        this.actor = actor;
        this.mailbox.setActor(actor);
    }

    public Mailbox getMailbox() {
        return this.mailbox;
    }

    @Override
    public String toString() {
        return this.actor.toString();
    }

    public void send(final Object message, final ActorRef sender) {
        final MessageEnvelope envelope = new MessageEnvelope(new MessageContextImpl(sender), message);
        this.dispatcher.dispatch(envelope, this);
    }
}

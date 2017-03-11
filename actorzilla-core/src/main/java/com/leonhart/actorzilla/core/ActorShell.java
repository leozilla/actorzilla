package com.leonhart.actorzilla.core;

/**
 * Created by david on 11.03.2017.
 */
class ActorShell {
    private final Mailbox mailbox;
    private final String name;

    ActorShell(final Mailbox mailbox, final String name) {
        this.mailbox = mailbox;
        this.name = name;
    }

    public Mailbox getMailbox() {
        return this.mailbox;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

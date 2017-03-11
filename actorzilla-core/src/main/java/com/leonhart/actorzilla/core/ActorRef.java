package com.leonhart.actorzilla.core;

/**
 * Created by david on 11.03.2017.
 */
public abstract class ActorRef {

    private final ActorShell shell;

    protected ActorRef(final ActorShell shell) {
        this.shell = shell;
    }

    public abstract void send(final Object message, final ActorRef sender);

    public ActorShell getShell() {
        return this.shell;
    }
}

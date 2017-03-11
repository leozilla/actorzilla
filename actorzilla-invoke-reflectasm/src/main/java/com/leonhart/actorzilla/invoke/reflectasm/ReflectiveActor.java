package com.leonhart.actorzilla.invoke.reflectasm;

import com.leonhart.actorzilla.core.Actor;
import com.leonhart.actorzilla.core.MessageContext;

/**
 * Created by david on 11.03.2017.
 */
public abstract class ReflectiveActor extends Actor {

    private final ReflectAsmActorMetadata metadata;

    public ReflectiveActor() {
        this.metadata = new ReflectAsmActorMetadata(this);
    }

    public ReflectAsmActorMetadata getMetadata() {
        return this.metadata;
    }

    @Override
    protected void receive(final MessageContext ctx, final Object message) {
        // TODO unhandled
    }

}

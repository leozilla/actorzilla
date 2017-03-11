package com.leonhart.actorzilla.invoke.reflectasm;

import com.leonhart.actorzilla.core.Actor;
import com.leonhart.actorzilla.core.MessageContext;

/**
 * Created by david on 11.03.2017.
 */
public abstract class RejectingActor extends Actor {

    @Override
    public void receive(final MessageContext ctx, final Object message) {
    }
}

package com.leonhart.actorzilla.core;

/**
 * Created by david on 11.03.2017.
 */
public interface Invoker<A extends Actor> {

    void invoke(final A actorTarget, final MessageContext messageContext, final Object message);

    Invoker DEFAULT = new DefaultInvoker();

    class DefaultInvoker implements Invoker<Actor> {

        @Override
        public void invoke(final Actor actorTarget, final MessageContext messageContext, final Object message) {
            actorTarget.receive(messageContext, message);
        }
    }
}

package com.leonhart.actorzilla.core;

/**
 * Created by david on 11.03.2017.
 */
public interface Invoker {

    void invoke(final Actor actorTarget, final MessageContext messageContext, final Object message);

    Invoker DEFAULT = new DefaultInvoker();

    class DefaultInvoker implements Invoker {

        @Override
        public void invoke(final Actor actorTarget, final MessageContext messageContext, final Object message) {
            actorTarget.receive(messageContext, message);
        }
    }
}

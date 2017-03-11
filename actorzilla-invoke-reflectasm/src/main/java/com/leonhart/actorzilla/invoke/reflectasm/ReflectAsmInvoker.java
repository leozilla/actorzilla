package com.leonhart.actorzilla.invoke.reflectasm;

import com.leonhart.actorzilla.core.Invoker;
import com.leonhart.actorzilla.core.MessageContext;

import java.util.Optional;

/**
 * Created by david on 11.03.2017.
 */
public class ReflectAsmInvoker implements Invoker<ReflectiveActor> {

    public static final ReflectAsmInvoker INSTANCE = new ReflectAsmInvoker();

    @Override
    public void invoke(final ReflectiveActor actorTarget, final MessageContext messageContext, final Object message) {
        final Optional<InvocableMethod> method = actorTarget.getMetadata().findMethod(message);
        if (method.isPresent()) {
            method.get().invoke(actorTarget, messageContext, message);
        } else {
            // TODO
        }
    }
}

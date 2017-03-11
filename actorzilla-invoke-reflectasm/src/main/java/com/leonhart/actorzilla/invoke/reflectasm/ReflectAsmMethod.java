package com.leonhart.actorzilla.invoke.reflectasm;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.leonhart.actorzilla.core.MessageContext;

/**
 * Created by david on 11.03.2017.
 */
public class ReflectAsmMethod implements InvocableMethod {
    private final MethodAccess methodAccess;
    private final int index;

    public ReflectAsmMethod(final MethodAccess methodAccess, final int methodIndex) {
        this.methodAccess = methodAccess;
        this.index = methodIndex;
    }

    @Override
    public void invoke(final Object actor, final MessageContext messageContext, final Object message) {
        try {
            this.methodAccess.invoke(actor, this.index, messageContext, message);
        } catch (final Exception ex) {
            ex.printStackTrace(); // TODO
        }
    }
}

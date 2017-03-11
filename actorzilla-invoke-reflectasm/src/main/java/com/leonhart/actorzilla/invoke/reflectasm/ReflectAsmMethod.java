package com.leonhart.actorzilla.invoke.reflectasm;

import com.esotericsoftware.reflectasm.MethodAccess;

/**
 * Created by david on 11.03.2017.
 */
public class ReflectAsmMethod implements InvokableMethod {
    private final MethodAccess methodAccess;
    private final int index;

    public ReflectAsmMethod(final MethodAccess methodAccess, final int methodIndex) {
        this.methodAccess = methodAccess;
        this.index = methodIndex;
    }

    @Override
    public void invoke(final Object target, final Object message) {
        try {
            this.methodAccess.invoke(target, this.index, null, message);
        } catch (final Exception ex) {
            ex.printStackTrace(); // TODO
        }
    }
}

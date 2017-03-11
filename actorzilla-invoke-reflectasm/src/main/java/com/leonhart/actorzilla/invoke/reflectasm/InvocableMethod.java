package com.leonhart.actorzilla.invoke.reflectasm;

import com.leonhart.actorzilla.core.MessageContext;

/**
 * Created by david on 11.03.2017.
 */
@FunctionalInterface
public interface InvocableMethod {
    void invoke(Object actor, final MessageContext messageContext, Object message);
}

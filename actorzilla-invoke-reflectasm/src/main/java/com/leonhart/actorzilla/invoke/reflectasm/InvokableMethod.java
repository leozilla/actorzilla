package com.leonhart.actorzilla.invoke.reflectasm;

/**
 * Created by david on 11.03.2017.
 */
public interface InvokableMethod {
    void invoke(Object target, Object message);
}
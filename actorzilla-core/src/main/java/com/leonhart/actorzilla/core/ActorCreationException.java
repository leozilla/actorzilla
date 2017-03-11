package com.leonhart.actorzilla.core;

/**
 * Created by david on 11.03.2017.
 */
public class ActorCreationException extends RuntimeException {
    public ActorCreationException(final Exception e) {
        super(e);
    }
}

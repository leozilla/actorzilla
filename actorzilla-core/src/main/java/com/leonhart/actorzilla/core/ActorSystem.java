package com.leonhart.actorzilla.core;

import java.util.function.Function;

public class ActorSystem {
    private final Dispatcher defaultDispatcher = new DirectDispatcher(new DirectExecutorService());

    public <T extends Actor> ActorRef createActor(final Class<T> actorClass) {
        final T actor = tryCreateActorOrThrow(actorClass);
        return new LocalActorRef(this.defaultDispatcher, actor);
    }

    public <T extends Actor> ActorRef createActor(final Class<T> actorClass, final Function<T, Actor> decorator) {
        final T actor = tryCreateActorOrThrow(actorClass);
        return new LocalActorRef(this.defaultDispatcher, decorator.apply(actor));
    }

    private <T extends Actor> T tryCreateActorOrThrow(final Class<T> actorClass) {
        final T actor;

        try {
            actor = actorClass.newInstance();
        } catch (final InstantiationException | IllegalAccessException e) {
            throw new ActorCreationException(e);
        }

        return actor;
    }
}

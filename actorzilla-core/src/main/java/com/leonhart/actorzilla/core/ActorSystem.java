package com.leonhart.actorzilla.core;

public class ActorSystem {

    public <T extends Actor> ActorRef createActor(final Class<T> actorClass) {
        return createActor(actorClass, ActorProps.DEFAULT.get());
    }

    public <T extends Actor> ActorRef createActor(final Class<T> actorClass, final ActorProps props) {
        final T actor = tryCreateActorOrThrow(actorClass);
        return new LocalActorRef(props.dispatcher, props.invoker, props.createMailbox(), actor);
    }

    public <T extends Actor> ActorRef createActor(final T actor) {
        return createActor(actor, ActorProps.DEFAULT.get());
    }

    public <T extends Actor> ActorRef createActor(final T actor, final ActorProps props) {
        return new LocalActorRef(props.dispatcher, props.invoker, props.createMailbox(), actor);
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

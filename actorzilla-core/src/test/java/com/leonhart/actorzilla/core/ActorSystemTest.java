package com.leonhart.actorzilla.core;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Created by david on 11.03.2017.
 */
public class ActorSystemTest extends Actor {

    HelloResponse receivedResponse;

    private static class HelloRequest {
    }

    private static class HelloResponse {
    }

    public static class HelloActor extends Actor {

        @Override
        public void receive(final MessageContext ctx, final Object message) {
            if (message instanceof HelloRequest) {
                getSender().send(new HelloResponse(), getSelf());
            }
        }
    }

    @Override
    public void receive(final MessageContext ctx, final Object message) {
        if (message instanceof HelloResponse) {
            this.receivedResponse = (HelloResponse) message;
        }
    }

    @Test
    public void requestResponse() {
        final ActorSystem system = new ActorSystem();
        final ActorRef helloActor = system.createActor(HelloActor.class);
        final ActorRef self = system.createActor(ActorSystemTest.class);

        helloActor.send(new HelloRequest(), self);

        final LocalActorRef localRef = (LocalActorRef) self;
        final ActorSystemTest actorTest = (ActorSystemTest) localRef.getActor();
        assertThat(actorTest.receivedResponse, instanceOf(HelloResponse.class));
    }
}
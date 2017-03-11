package com.leonhart.actorzilla.invoke.reflectasm;

import com.leonhart.actorzilla.core.*;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class ReflectAsmTest extends Actor {

    static HelloResponse receivedResponse;

    private static class HelloRequest {

    }

    private static class HelloResponse {

    }

    public static class HelloActor extends Actor {

        @EventHandler
        public void handle(final MessageContext ctx, final HelloRequest request) {
            getSender().send(new HelloResponse(), getSelf());
        }

        @Override
        public void receive(final MessageContext ctx, final Object message) {
        }

    }

    @EventHandler
    public void handle(final MessageContext ctx, final HelloResponse response) {
        this.receivedResponse = response;
    }

    @Override
    public void receive(final MessageContext ctx, final Object message) {
    }

    @Test
    public void test() {
        final ActorSystem system = new ActorSystem();
        final ActorRef actorRef = system.createActor(HelloActor.class, ReflectiveActorDecorator::new);
        final ActorRef self = system.createActor(ReflectAsmTest.class, ReflectiveActorDecorator::new);

        actorRef.send(new HelloRequest(), self);

        assertThat(receivedResponse, instanceOf(HelloResponse.class));
    }
}
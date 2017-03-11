package com.leonhart.actorzilla.invoke.reflectasm;

import com.leonhart.actorzilla.core.Actor;
import com.leonhart.actorzilla.core.ActorRef;
import com.leonhart.actorzilla.core.ActorSystem;
import com.leonhart.actorzilla.core.MessageContext;
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
            ctx.getSender().send(new HelloResponse(), getSelf());
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
    public void invokeViaReflectAsm() {
        final ActorSystem system = new ActorSystem();
        final ActorRef actorRef = system.createActor(HelloActor.class, actor -> new ReflectAsmInvoker(actor));
        final ActorRef self = system.createActor(ReflectAsmTest.class, actor -> new ReflectAsmInvoker(actor));

        actorRef.send(new HelloRequest(), self);

        assertThat(receivedResponse, instanceOf(HelloResponse.class));
    }
}
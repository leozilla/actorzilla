package com.leonhart.actorzilla.invoke.reflectasm;

import com.leonhart.actorzilla.core.*;
import com.leonhart.actorzilla.testing.DirectExecutorService;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class ReflectAsmTest {

    private static class PingRequest {
    }

    private static class PingResponse {
    }

    public static class PingActor extends ReflectiveActor {

        @MessageHandler
        public void handle(final MessageContext ctx, final PingRequest request) {
            ctx.getSender().send(new PingResponse(), getSelf());
        }

    }

    public static class PongActor extends ReflectiveActor {
        static PingResponse receivedResponse;

        @MessageHandler
        public void handle(final MessageContext ctx, final PingResponse response) {
            this.receivedResponse = response;
        }

    }

    @Test
    public void invokeViaReflectAsm() {
        final ActorSystem system = new ActorSystem();
        final DirectDispatcher dispatcher = new DirectDispatcher(new DirectExecutorService());
        final ActorRef actorRef = system.createActor(PingActor.class, ActorProps.newProps().withInvoker(ReflectAsmInvoker.INSTANCE).withDispatcher(dispatcher));
        final ActorRef self = system.createActor(PongActor.class, ActorProps.newProps().withInvoker(ReflectAsmInvoker.INSTANCE).withDispatcher(dispatcher));

        actorRef.send(new PingRequest(), self);

        assertThat(PongActor.receivedResponse, instanceOf(PingResponse.class));
    }
}
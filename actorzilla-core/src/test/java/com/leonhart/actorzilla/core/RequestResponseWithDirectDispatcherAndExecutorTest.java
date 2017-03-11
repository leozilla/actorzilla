package com.leonhart.actorzilla.core;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Created by david on 11.03.2017.
 */
public class RequestResponseWithDirectDispatcherAndExecutorTest {

    private static class PingRequest {
    }

    private static class PingResponse {
    }

    public static class PingActor extends Actor {

        @Override
        public void receive(final MessageContext ctx, final Object message) {
            if (message instanceof PingRequest) {
                ctx.getSender().send(new PingResponse(), getSelf());
            }
        }
    }

    public static class PongActor extends Actor {
        static PingResponse receivedResponse;

        @Override
        protected void receive(final MessageContext ctx, final Object message) {
            if (message instanceof PingResponse) {
                this.receivedResponse = (PingResponse) message;
            }
        }
    }

    @Test
    public void requestResponse() {
        final ActorSystem system = new ActorSystem();
        final ActorRef ping = system.createActor(PingActor.class);
        final ActorRef pong = system.createActor(PongActor.class);

        ping.send(new PingRequest(), pong);

        assertThat(PongActor.receivedResponse, instanceOf(PingResponse.class));
    }
}
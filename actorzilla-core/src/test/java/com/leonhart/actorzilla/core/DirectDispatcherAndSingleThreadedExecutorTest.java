package com.leonhart.actorzilla.core;

import com.leonhart.actorzilla.core.dispatch.ConcurrentLinkedMessageQueue;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Created by david on 11.03.2017.
 */
public class DirectDispatcherAndSingleThreadedExecutorTest {
    private static final CountDownLatch latch = new CountDownLatch(1);

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
                latch.countDown();
            }
        }
    }

    @Test
    public void requestResponse() throws InterruptedException {
        final ActorSystem system = new ActorSystem();
        final Dispatcher singleThreadDispatcher = new DirectDispatcher(Executors.newSingleThreadExecutor());
        final ActorRef ping = system.createActor(PingActor.class, ActorProps.newProps().withDispatcher(singleThreadDispatcher).withMessageQueue(new ConcurrentLinkedMessageQueue()));
        final ActorRef pong = system.createActor(PongActor.class, ActorProps.newProps().withDispatcher(singleThreadDispatcher).withMessageQueue(new ConcurrentLinkedMessageQueue()));

        ping.send(new PingRequest(), pong);

        final boolean received = latch.await(3, TimeUnit.SECONDS);
        assertThat(received, equalTo(true));
        assertThat(PongActor.receivedResponse, instanceOf(PingResponse.class));
    }
}

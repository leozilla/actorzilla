package com.leonhart.actorzilla.core;

import com.leonhart.actorzilla.core.dispatch.ConcurrentLinkedMessageQueue;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Created by david on 11.03.2017.
 */
public class AskTest {

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

    @Test
    public void ask_actorsAnswers_completeFuture() throws InterruptedException, ExecutionException, TimeoutException {
        final ActorSystem system = new ActorSystem();
        final Dispatcher singleThreadDispatcher = new DirectDispatcher(Executors.newSingleThreadExecutor());
        final ActorRef ping = system.createActor(PingActor.class, ActorProps.newProps()
                .withDispatcher(singleThreadDispatcher)
                .withMessageQueue(new ConcurrentLinkedMessageQueue()));

        final CompletableFuture<Object> future = ping.ask(new PingRequest(), null);

        final Object result = future.get(3, TimeUnit.SECONDS);
        assertThat(result, instanceOf(PingResponse.class));
    }
}

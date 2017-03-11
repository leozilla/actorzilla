package com.leonhart.actorzilla.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.leonhart.actorzilla.core.*;
import com.leonhart.actorzilla.testing.DirectExecutorService;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;

/**
 * Created by david on 11.03.2017.
 */
public class GuiceTest {

    private static class PingRequest {
    }

    private static class PingResponse {
    }

    public interface Dependency {
        void received(Object message);
    }

    public static class Implementation implements Dependency {
        Object receivedMessage;

        @Override
        public void received(final Object message) {
            this.receivedMessage = message;
        }
    }

    private static class TestModule extends AbstractModule {
        private final Implementation testImpl;

        TestModule(final Implementation testImpl) {
            this.testImpl = testImpl;
        }

        @Override
        protected void configure() {
            bind(Dependency.class).toInstance(this.testImpl);
        }
    }

    public static class PingActor extends Actor {
        private final Dependency dependency;

        @Inject
        public PingActor(final Dependency dependency) {
            this.dependency = dependency;
        }

        @Override
        protected void receive(final MessageContext ctx, final Object message) {
            this.dependency.received(message);
            ctx.getSender().send(new PingResponse(), getSelf());
        }
    }

    @Test
    public void canCreateActorViaGuice() {
        final Implementation testImpl = new Implementation();

        final Injector injector = Guice.createInjector(new TestModule(testImpl));

        final ActorSystem system = new ActorSystem();
        final ActorRef actorRef = system.createActor(injector.getInstance(PingActor.class), ActorProps.newProps().withDispatcher(new DirectDispatcher(new DirectExecutorService())));

        final PingRequest request = new PingRequest();
        actorRef.send(request, null);

        assertThat(testImpl.receivedMessage, sameInstance(request));
    }
}
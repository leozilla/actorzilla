package com.leonhart.actorzilla.core;

import com.leonhart.actorzilla.core.dispatch.MessageQueue;
import com.leonhart.actorzilla.core.dispatch.UnboundedArrayListMessageQueue;

import java.util.function.Supplier;

/**
 * Created by david on 11.03.2017.
 */
public class ActorProps {
    static final Dispatcher defaultDispatcher = new DirectDispatcher(new DirectExecutorService());

    public static Supplier<ActorProps> DEFAULT = () -> new ActorProps()
            .withDispatcher(defaultDispatcher)
            .withInvoker(Invoker.DEFAULT)
            .withMessageQueue(UnboundedArrayListMessageQueue::new);

    private Supplier<MessageQueue> messageQueueFactory;

    Dispatcher dispatcher;
    Invoker invoker;

    public static ActorProps newProps() {
        return DEFAULT.get();
    }

    public ActorProps withDispatcher(final Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        return this;
    }

    public ActorProps withMessageQueue(final MessageQueue messageQueue) {
        this.messageQueueFactory = () -> messageQueue;
        return this;
    }

    public ActorProps withInvoker(final Invoker invoker) {
        this.invoker = invoker;
        return this;
    }

    public ActorProps withMessageQueue(final Supplier<MessageQueue> mailboxFactory) {
        this.messageQueueFactory = mailboxFactory;
        return this;
    }

    public MessageQueue createMailbox() {
        return this.messageQueueFactory.get();
    }
}

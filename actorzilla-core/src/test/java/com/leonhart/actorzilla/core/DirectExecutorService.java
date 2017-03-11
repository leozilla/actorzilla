package com.leonhart.actorzilla.core;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

class DirectExecutorService extends AbstractExecutorService {
    private boolean terminated = false;

    @Override
    public void shutdown() {
        this.terminated = true;
    }

    @Override
    public List<Runnable> shutdownNow() {
        shutdown();
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean isShutdown() {
        return this.terminated;
    }

    @Override
    public boolean isTerminated() {
        return this.terminated;
    }

    @Override
    public boolean awaitTermination(final long timeout, final TimeUnit unit) throws InterruptedException {
        return this.terminated;
    }

    @Override
    public void execute(final Runnable command) {
        command.run();
    }
}

package org.example;

import threads.accessors.Consumer;
import threads.accessors.Producer;
import threads.buffers.Buffer;
import threads.buffers.SharedBuffer;
import threads.soures.DataSource;

import java.nio.BufferOverflowException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new SharedBuffer();

        DataSource dataSource = new DataSource();
        Producer producer = new Producer(buffer, dataSource);
        Consumer consumer = new Consumer(buffer);

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(producer);
        executorService.execute(consumer);

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
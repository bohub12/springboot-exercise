package com.example.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class ScheduledThreadPoolExecutorTest {

    @Test
    @Timeout(1000)
    void whenSchedulingTask_thenTaskExecutesWithinGivenPeriod() throws InterruptedException {

        CountDownLatch lock = new CountDownLatch(1);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        executor.schedule(() -> {
            System.out.println("Hello World");
            lock.countDown();
        }, 500, TimeUnit.MILLISECONDS);

        lock.await(1000, TimeUnit.MILLISECONDS);
    }

    @Test
    @Timeout(1000)
    void whenSchedulingTaskWithFixedPeriod_thenTaskExecutesMultipleTimes() throws InterruptedException {

        CountDownLatch lock = new CountDownLatch(3);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
            System.out.println("Hello World");
            lock.countDown();
        }, 500, 100, TimeUnit.MILLISECONDS);

        lock.await();
        future.cancel(true);
    }
}

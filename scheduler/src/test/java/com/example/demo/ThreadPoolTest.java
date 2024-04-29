package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class ThreadPoolTest {

    /**
     * Executors.newFixedThreadPool() method creates a ThreadPoolExecutor with equal corePoolSize and maximumPoolSize parameter values and a zero keepAliveTime.
     */
    @Test
    void fixedThreadPoolTest() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        assertEquals(2, executor.getPoolSize());
        assertEquals(1, executor.getQueue().size());
    }

    /**
     * Executors.newCachedThreadPool() method does not receive a number of threads at all.
     * It set the corePoolSize to 0 and set the maximumPoolSize to Integer.MAX_VALUE. Finally, the keepAliveTime is 60 seconds:
     */
    @Test
    void cachedThreadPoolTest() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        assertEquals(3, executor.getPoolSize());
        assertEquals(0, executor.getQueue().size());
    }

    /**
     * The single thread executor is ideal for creating an event loop. The corePoolSize and maximumPoolSize parameters are equal to 1, and the keepAliveTime is 0.
     * The corePoolSize and maximumPoolSize parameters are equal to 1, and the keepAliveTime is 0.
     */
    @Test
    @Timeout(1000)
    void singleThreadPoolTest() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(2);
        AtomicInteger counter = new AtomicInteger();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            counter.set(1);
            lock.countDown();
        });
        executor.submit(() -> {
            counter.compareAndSet(1, 2);
            lock.countDown();
        });

        lock.await(1000, TimeUnit.MILLISECONDS);
        assertEquals(2, counter.get());
    }
}

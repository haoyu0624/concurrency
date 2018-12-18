package com.hy.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 *  StampedLock 使用
 */
@Slf4j
public class LockExample3 {


    //请求总数
    public static int clientTotal = 5000;

    //并发执行线程数
    public static int threadTotal = 200;

    public static int count = 0;

    private static final StampedLock lock = new StampedLock();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e){
                    log.info(e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count is {}",count);
    }

    private static void add(){
        long l = lock.writeLock();
        try {
            count++;
        }finally {
            lock.unlock(l);
        }

    }
}

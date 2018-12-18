package com.hy.concurrency.example.atomic;

import com.hy.concurrency.annoactions.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@ThreadSafe
public class AtomicExample6 {

    /**
     * 目的是多线程情况下，只需要执行一次此方法时可以用此类
     */
    private static AtomicBoolean isHappen = new AtomicBoolean(false);

    //请求总数
    public static int clientTotal = 5000;

    //并发执行线程数
    public static int threadTotal = 200;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                }catch (Exception e){
                    log.info(e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("isHappen is {}",isHappen);
    }

    private static void test() {
        if(isHappen.compareAndSet(false,true)){
            log.info("execute...");
        }
    }
}

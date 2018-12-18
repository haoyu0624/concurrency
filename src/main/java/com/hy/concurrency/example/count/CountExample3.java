package com.hy.concurrency.example.count;

import com.hy.concurrency.annoactions.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ThreadSafe
public class CountExample3 {

    //请求总数
    public static int clientTotal = 5000;

    //并发执行线程数
    public static int threadTotal = 200;

    //不具有原子性
    public static volatile int count = 0;

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
        log.info("count is {}",count);
    }

    private static void add(){
        count++;
        //1.获取count值 尽管两个线程都同时读取了最新的值，但是后续他们都会+1后写入主存所以丢失了一次+1的机会
        //2.+1
        //3.写回count值
    }
}

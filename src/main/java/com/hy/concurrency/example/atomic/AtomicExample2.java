package com.hy.concurrency.example.atomic;

import com.hy.concurrency.annoactions.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@ThreadSafe
public class AtomicExample2 {

    //请求总数
    public static int clientTotal = 5000;

    //并发执行线程数
    public static int threadTotal = 200;

    public static AtomicLong count = new AtomicLong(0);

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
        count.incrementAndGet();
        /**
         * var1 是count对象
         * var2 是count的值
         * var4 是要想加的值默认是1
         * var5 是底层取到的count的值
         */
//        public final int getAndAddInt(Object var1, long var2, int var4) {
//            int var5;
//            do {
//                var5 = this.getIntVolatile(var1, var2);
              //如果不匹配返回修改前value的值，也就是var2的值
//            } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
//
//            return var5;
//        }
    }
}

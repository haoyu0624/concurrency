package com.hy.concurrency.example.count;

import com.hy.concurrency.annoactions.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class CountExample4 {

    //请求总数
    public static int clientTotal = 1000;

    //并发执行线程数
    public static int threadTotal = 200;

    //不具有原子性
    public static Map count = new HashMap();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int j = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(j);
                    semaphore.release();
                }catch (Exception e){
                    log.info(e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        catMap();
        log.info("count is {}",count.size());
    }

    private static void update(int i){
        count.put(i,i);

    }

    private static void catMap(){
        Set set = count.keySet();
        log.info("set is {}",set.size());
        for(int i=0;i <1000;i++){
            if(set.contains(i)){
//                System.out.println("是*******:"+i);
            }else{
                System.out.println("非-------------------------------------------------:"+i);
            }
        }

    }
}

package com.hy.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureExample {

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do something");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorsService = Executors.newCachedThreadPool();
        Future<String> future = executorsService.submit(new MyCallable());
        log.info("do something in main");
        Thread.sleep(1000);
        String result = future.get();
        log.info("result is {}.", result);

    }
}

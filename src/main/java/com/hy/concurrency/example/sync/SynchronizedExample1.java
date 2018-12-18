package com.hy.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedExample1 {

    /**
     * 修饰一个代码块
     */
    public void test1(){
        synchronized (this){
            //...
        }
    }

    /**
     * 修饰一个方法
     */
    public synchronized void test2(){

    }
}

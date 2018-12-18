package com.hy.concurrency.example.singleton;

import com.hy.concurrency.annoactions.NotThreadSafe;
import com.hy.concurrency.annoactions.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
/**
 * 懒汉模式->双重同步锁单例模式  但是！！它不是绝对的线程安全 但是使用volatile就不会出现此问题
 */
public class SingletonExample5 {

    //1.私有构造函数
    private SingletonExample5() {
    }

    //2.单例对象  volatile + 双重检测机制 -> 禁止指令重排
    private volatile static SingletonExample5 instance = null;

    //3.静态的工厂方法
    //双重检测机制
    public static SingletonExample5 getInstance(){
        if(null == instance){
            synchronized (SingletonExample5.class){
                if(null == instance){
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) {

    }


}

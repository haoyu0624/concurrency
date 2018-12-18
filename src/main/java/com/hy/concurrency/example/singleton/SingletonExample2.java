package com.hy.concurrency.example.singleton;

import com.hy.concurrency.annoactions.NotThreadSafe;
import com.hy.concurrency.annoactions.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
/**
 * 饿汉模式：实例在类装载的时候进行创建
 * 虽然是线程安全的但是有两个缺陷，以及要注意
 *  1.构造函数时不要有太多的逻辑
 *  2.不使用此类创建实例时也会创建对象，浪费资源
 */
public class SingletonExample2 {

    //1.私有构造函数
    private SingletonExample2() {
    }

    //2.单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    //3.静态的工厂方法
    public static SingletonExample2 getInstance(){
        return instance;
    }


    public static void main(String[] args) {

    }


}

package com.hy.concurrency.example.singleton;

import com.hy.concurrency.annoactions.NotThreadSafe;
import com.hy.concurrency.annoactions.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
/**
 * 懒汉模式（线程安全版本.但性能不够好）
 * 单例模式的前提是必须要有以下三个步骤
 * 懒汉模式：第一次使用时才创建实例
 */
public class SingletonExample3 {

    //1.私有构造函数
    private SingletonExample3() {
    }

    //2.单例对象
    private static SingletonExample3 instance = null;

    //3.静态的工厂方法
    public static synchronized SingletonExample3 getInstance(){
        if(null == instance){
            instance = new SingletonExample3();
        }
        return instance;
    }


    public static void main(String[] args) {

    }


}

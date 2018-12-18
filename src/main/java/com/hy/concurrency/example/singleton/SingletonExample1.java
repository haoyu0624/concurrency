package com.hy.concurrency.example.singleton;

import com.hy.concurrency.annoactions.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
/**
 * 单例模式的前提是必须要有以下三个步骤
 * 懒汉模式：第一次使用时才创建实例
 */
public class SingletonExample1 {

    //1.私有构造函数
    private SingletonExample1() {
    }

    //2.单例对象
    private static SingletonExample1 instance = null;

    //3.静态的工厂方法
    public static SingletonExample1 getInstance(){
        if(null == instance){
            instance = new SingletonExample1();
        }
        return instance;
    }


    public static void main(String[] args) {

    }


}

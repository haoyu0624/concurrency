package com.hy.concurrency.example.singleton;

import com.hy.concurrency.annoactions.Recommend;
import com.hy.concurrency.annoactions.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
@Recommend
/**
 * 枚举模式：最安全
 * 恶汉模式
 */
public class SingletonExample7 {

    private SingletonExample7() {
    }

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        //JVM 保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance(){
            return singleton;
        }
    }


    public static void main(String[] args) {
        System.out.println(SingletonExample7.getInstance());
        System.out.println(SingletonExample7.getInstance());
    }


}

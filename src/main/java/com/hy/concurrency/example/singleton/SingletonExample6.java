package com.hy.concurrency.example.singleton;

import com.hy.concurrency.annoactions.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
/**
 * 恶汉模式--在static中初始化
 */
public class SingletonExample6 {

    private SingletonExample6() {
    }

    private static SingletonExample6 instance = null;

    //静态域的方式是有顺序的，要将private static SingletonExample6 instance = null;放在静态域的前边，否则获取不到实例
    static {
        instance = new SingletonExample6();
    }



    public static SingletonExample6 getInstance(){
        return instance;
    }


    public static void main(String[] args) {
        System.out.println(SingletonExample6.getInstance());
        System.out.println(SingletonExample6.getInstance());
    }


}

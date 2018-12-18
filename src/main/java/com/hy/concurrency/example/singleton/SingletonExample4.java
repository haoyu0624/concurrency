package com.hy.concurrency.example.singleton;

import com.hy.concurrency.annoactions.NotThreadSafe;
import com.hy.concurrency.annoactions.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
/**
 * 懒汉模式->双重同步锁单例模式  但是！！它不是绝对的线程安全
 */
public class SingletonExample4 {

    //1.私有构造函数
    private SingletonExample4() {
    }

    //2.单例对象
    private static SingletonExample4 instance = null;

    //3.静态的工厂方法
    //双重检测机制
    public static SingletonExample4 getInstance(){
        if(null == instance){
            synchronized (SingletonExample4.class){
                if(null == instance){
                    /**
                     * 创建实例的步骤分为以下三步
                     * 1.memory = allocate()分配对象的内存空间
                     * 2.Instance() 初始化对象
                     * 3.instance = memory 设置memory指向刚分配的实例
                     *
                     * jvm和cpu优化，发生了指令重排序
                     * 1.memory = allocate()分配对象的内存空间
                     * 2.instance = memory 设置memory指向刚分配的实例
                     * 3.Instance() 初始化对象
                     * 第一个线程有可能还没有真正实例化对象第三部，第二个线程一看instance不为空就去返回对象，但是此实例还未真正创建无法使用，有线程安全隐患
                     */
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) {

    }


}

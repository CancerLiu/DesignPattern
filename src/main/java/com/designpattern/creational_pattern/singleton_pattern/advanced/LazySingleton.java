package com.designpattern.creational_pattern.singleton_pattern.advanced;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 懒汉模式
 */
public class LazySingleton {

    //因为使用了双重检查锁定，所以需要使用volatile修饰符。可以保证该实例会被马上更新。
    private volatile static LazySingleton singleton = null;

    private LazySingleton() {
    }

    //懒汉模式可能会有线程安全的问题，所以这里通过双重检查锁定(Double-Check  Locking)来规避
    public static LazySingleton getSingleton() {
        if (null == singleton) {
            synchronized (LazySingleton.class) {
                if (null == singleton) {
                    singleton = new LazySingleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        //开四个线程进行多线程的测试
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 5; i++) {
            Integer index = i;
            executor.submit(() -> {
                LazySingleton obj = LazySingleton.getSingleton();
                System.out.println(Thread.currentThread().getName() + "_" + index + "_" + obj);
            });
        }
    }
}

package com.designpattern.creational_pattern.singleton_pattern.advanced;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 简称IoDH的方法，可以同时克服懒汉模式和饿汉模式的缺点
 */
public class IoDHSingleton {
    //仍然私有化构造器
    private IoDHSingleton() {
    }

    //关键所在，引入静态内部类
    private static class HolderClass {
        private final static IoDHSingleton instance = null;
    }

    //相同的对外提供唯一实例的方法
    public static IoDHSingleton getInstance() {
        return HolderClass.instance;
    }

    public static void main(String[] args) {
        //开四个线程进行多线程的测试
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 3; i++) {
            Integer index = i;
            executor.submit(() -> {
                LazySingleton obj = LazySingleton.getSingleton();
                System.out.println(Thread.currentThread().getName() + "_" + index + "_" + obj);
            });
        }
    }
}

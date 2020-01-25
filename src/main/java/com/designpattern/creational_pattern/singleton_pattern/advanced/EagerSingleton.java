package com.designpattern.creational_pattern.singleton_pattern.advanced;

/**
 * 饿汉模式
 */
public class EagerSingleton {

    //因为之后不会有修改，所以可以加final
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
    }

    //因为是static和final修饰的，所以连判null都不用
    public static EagerSingleton getInstance() {
        return instance;
    }
}

package com.designpattern.creational_pattern.singleton_pattern;

public class BaseCodeForm {

    //私有化成员变量
    private static BaseCodeForm instance = null;

    //私有化构造器
    private BaseCodeForm() {
    }

    //对外提供一个共有方法，提供唯一的对象

    public static BaseCodeForm getInstance() {
        if (null == instance) {
            instance = new BaseCodeForm();
        }
        return instance;
    }

    public static void main(String[] args) {
        BaseCodeForm obj1 = BaseCodeForm.getInstance();
        BaseCodeForm obj2 = BaseCodeForm.getInstance();
        BaseCodeForm obj3 = BaseCodeForm.getInstance();

        //相同的对象其地址一定是一样的
        System.out.println(obj1);
        System.out.println(obj2);
        System.out.println(obj3);
    }
}

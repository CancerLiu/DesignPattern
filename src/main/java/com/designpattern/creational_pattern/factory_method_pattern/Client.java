package com.designpattern.creational_pattern.factory_method_pattern;


import com.designpattern.utils.PropertiesUtil;
import com.designpattern.utils.ReflectUtils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 客户端类
 * 这里工厂方法类有两个小小的灵活变换:
 * ①在工厂类中提供重载方法，这样可以做到见方法名知意;
 * ②在工厂类中，不直接返回具体产品，而是返回具体产品调用相关方法之后的结果。
 */
public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //总的来说就是，通过配置文件先找到工厂，再通过工厂得到具体的产品

        //两个抽象类
        BaseCodeForm.AbstractShape shape = null;
        BaseCodeForm.AbstractFactory factory = null;

        //通过配置文件和反射技术得到具体工厂实例，然后用这个实例生产对应的方法
        String outerClassName = PropertiesUtil.getValue("outer.factory.name");
        //指定具体的工厂类名，后面通过反射得到
        String innerClassName = PropertiesUtil.getValue("inner.factory.name");

        //得到外部类的Class对象与实例对象
        factory=  (BaseCodeForm.AbstractFactory) ReflectUtils.getInnerClass(outerClassName,innerClassName);


        //通过具体工厂类得到具体要生产的产品
        if (factory != null) {
            shape = factory.createShape();
        } else {
            System.out.println("所给参数找不到内部类");
        }
        //开始生产
        shape.display();
    }
}

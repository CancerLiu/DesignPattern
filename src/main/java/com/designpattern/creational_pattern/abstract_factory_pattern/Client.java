package com.designpattern.creational_pattern.abstract_factory_pattern;



import com.designpattern.utils.PropertiesUtil;
import com.designpattern.utils.ReflectUtils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 模拟客户端类，用于展示抽象工厂模式的使用
 */
public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //对象工厂方法，这里也是通过配置文件得到一个工厂，然后通过这个工厂得到一个产品族。
        // 然后调用产品族中各个产品的相关方法。


        //两个抽象类
        BaseCodeForm.AbstractShape shape = null;
        BaseCodeForm.AbstractColor color = null;
        BaseCodeForm.AbstractFactory factory = null;

        //通过配置文件和反射技术得到具体工厂实例，然后用这个实例生产对应的方法
        String outerClassName = PropertiesUtil.getValue("outer.abstract_factory.name");
        String innerClassName = PropertiesUtil.getValue("inner.abstract_factory.name");

        //得到外部类的Class对象与实例对象
        factory =  (BaseCodeForm.AbstractFactory) ReflectUtils.getInnerClass(outerClassName,innerClassName);


        //通过具体工厂类得到具体要生产的产品
        if (factory != null) {
            shape = factory.getShape();
            color = factory.getColor();
        } else {
            System.out.println("所给参数找不到内部类");
        }
        //开始生产
        color.display();
        shape.display();
    }
}

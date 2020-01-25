package com.designpattern.utils;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectUtils {

    public static Object getInnerClass(String outterFullyQualifiedClassName,String innerSimpleClassName
    ) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class factoryClass = Class.forName(outterFullyQualifiedClassName);
        Object outter =  factoryClass.newInstance();

        //通过反射得到内部类时，需要先得到外部类的Class对象
        Class<?>[] innerClass = factoryClass.getDeclaredClasses();
        for (Class cls : innerClass) {
            //通过外部类的Class得到内部类的对应构造器
            if (cls.getName().contains(innerSimpleClassName)) {
                Constructor con = cls.getDeclaredConstructor(factoryClass);
                con.setAccessible(true);
                //通过外部类实例和内部类构造器得到内部类实例
                return con.newInstance(outter);
            }
        }
        return null;
    }

    public static <T> Object getOutterClass(String fullyQualifiedClassName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
       return Class.forName(fullyQualifiedClassName).newInstance();
    }
}

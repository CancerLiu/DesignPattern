package com.designpattern.creational_pattern.static_factory_method;

/**
 * 静态工厂模式
 */
public class BaseCodeForm {
    //静态工厂方法一共有三个角色，工厂类、抽象产品类和具体产品类

    /**
     * 抽象产品类
     */
    public interface Product {
        void display();
    }

    /**
     * 具体产品类
     */
    public class Circle implements Product {

        @Override
        public void display() {
            System.out.println("画出一个圆形");
        }
    }

    /**
     * 具体产品类
     */
    public class Rectangle implements Product {

        @Override
        public void display() {
            System.out.println("画出一个矩形");
        }
    }

    /**
     * 具体产品类
     */
    public class triangle implements Product {

        @Override
        public void display() {
            System.out.println("画出一个三角形");
        }
    }
}

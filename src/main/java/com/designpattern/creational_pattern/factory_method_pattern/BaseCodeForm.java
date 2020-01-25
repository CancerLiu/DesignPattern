package com.designpattern.creational_pattern.factory_method_pattern;

/**
 * 工厂方法模式
 */
public class BaseCodeForm {
    //总的就是建立产品与工厂的两个抽象层，然后一个具体工厂对应一个具体产品
    /**
     * 抽象工厂类
     */
    public abstract class AbstractFactory {
        public abstract AbstractShape createShape();
    }

    /**
     * 抽象产品类
     */
    public abstract class AbstractShape {
        public abstract void display();
    }

    /**
     * 具体工厂类,对应一个具体产品
     */
    public class RectangleFactory extends AbstractFactory {

        @Override
        public AbstractShape createShape() {
            return new Rectangle();
        }
    }

    /**
     * 具体工厂类，对应一个具体产品类
     */
    public class CircleFactory extends AbstractFactory {

        public CircleFactory() {
        }

        @Override
        public AbstractShape createShape() {
            return new Circle();
        }
    }

    /**
     * 具体产品类
     */
    public class Rectangle extends AbstractShape {

        public Rectangle() {
        }

        @Override
        public void display() {
            System.out.println("画出一个矩形");
        }
    }

    /**
     * 具体产品类
     */
    public class Circle extends AbstractShape {

        @Override
        public void display() {
            System.out.println("画出一个圆形");
        }
    }
}

package com.designpattern.creational_pattern.abstract_factory_pattern;

/**
 * 抽象工厂模式
 * 相对于工厂方法模式，这里的一个具体工厂对应了一个产品族
 */
public class BaseCodeForm {

    /**
     * 抽象工厂类
     */
    public abstract class AbstractFactory {
        public abstract AbstractColor getColor();
        public abstract AbstractShape getShape();
    }

    /**
     * 抽象产品A类，用于从一个产品等级中挑选一个产品用于组成一个产品族
     * 这里来看，Color颜色就是一个产品等级。
     */
    public abstract class AbstractColor {
        public abstract void display();
    }

    /**
     * 抽象产品B类
     */
    public abstract class AbstractShape {
        public abstract void display();
    }

    public class Blue extends AbstractColor {

        @Override
        public void display() {
            System.out.println("蓝色的");
        }
    }

    public class Red extends AbstractColor {

        @Override
        public void display() {
            System.out.println("红色的");
        }
    }

    public class Rectangle extends AbstractShape {

        @Override
        public void display() {
            System.out.println("矩形");
        }
    }

    public class Circle extends AbstractShape {

        @Override
        public void display() {
            System.out.println("圆形");
        }
    }

    /**
     * 具体工厂，对应一个具体的产品族
     */
    public class ConcreteFactoryA extends AbstractFactory {

        @Override
        public AbstractColor getColor() {
            return new BaseCodeForm().new Blue();
        }

        @Override
        public AbstractShape getShape() {
            return new BaseCodeForm().new Rectangle();
        }
    }

    /**
     * 具体工厂B，用于生产另一条产品族
     */
    public class ConcreteFactoryB extends AbstractFactory {

        @Override
        public AbstractColor getColor() {
            return new BaseCodeForm().new Red();
        }

        @Override
        public AbstractShape getShape() {
            return new BaseCodeForm().new Circle();
        }
    }

}

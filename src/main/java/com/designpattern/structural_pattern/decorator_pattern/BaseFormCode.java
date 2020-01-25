package com.designpattern.structural_pattern.decorator_pattern;

/**
 * 装饰模式可以在不改变一个对象本身功能的基础上给对象增加额外的新行为
 * 该模式是一种用于替代继承的技术，使用对象之间的关联关系取代类之间的继承关系。在装饰类中既可以调用
 * 待装饰的原有类方法，也可以增加新的方法。
 *
 * 装饰模式有四个组件数，抽象构件、具体构件、抽象装饰类、具体装饰类
 *
 * 同时按照客户端能不能直接调用具体装饰类中的方法，将装饰类型分为透明和半透明
 * 前者就是如下，后者半透明具体需要使用实际具体装饰引用来对照具体装饰实例
 */
public class BaseFormCode {

    /**
     * 抽象构件类
     */
    abstract class Component{
        public abstract void display();
    }

    /**
     * 具体构件类——窗体类
     */
    class Windows extends Component{

        @Override
        public void display() {
            System.out.println("显示窗体");
        }
    }

    /**
     * 具体构件类——列表框类
     */
    class ListBox extends Component{

        @Override
        public void display() {
            System.out.println("显示列表框");
        }
    }

    /**
     * 具体构件类——文本框
     */
    class TextBox extends Component{

        @Override
        public void display() {
            System.out.println("显示文本框");
        }
    }

    /**
     * 抽象装饰类
     */
     class ComponentDecorator extends Component{
        //维持对抽象构件类的引用
         private Component component;
        //注入抽象构件对象

        public ComponentDecorator setComponent(Component component) {
            this.component = component;
            return this;
        }

        @Override
        public void display() {
            component.display();
        }
    }

    /**
     * 具体装饰类——滚动条
     */
    class ScrollBarDecorator extends ComponentDecorator{

        @Override
        public void display() {
            super.display();
            this.setScrollBar();
        }

        //装饰待加入的方法
        public  void setScrollBar(){
            System.out.println("为构件增加滚动条！");
        }

    }

    /**
     * 具体装饰类——黑边框
     */
    class BlackBorderDecorator extends ComponentDecorator{

        @Override
        public void display() {
            super.display();
            this.setBlackBorder();
        }

        public void setBlackBorder(){
            System.out.println("为构件增加黑色边框");
        }
    }

}



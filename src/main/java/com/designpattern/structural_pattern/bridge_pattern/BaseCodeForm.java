package com.designpattern.structural_pattern.bridge_pattern;

/**
 * 桥接模式用于一件事物有两个独立变化的维度的时候。该模式可以将这两个维度分离出来，使两者可以独立扩展
 *
 * 将两个独立变化的维度设计为两个独立的继承等级结构，并且在抽象层建立一个抽象关联，该关联关系类似一条连接
 * 两个独立继承结构的桥，故名桥接模式。
 *
 * 对客户端来说，可以针对两个维度的抽象层编程，在程序运行时再动态确定两个维度的子类，动态组合。
 * 将两个独立变化的维度完全解耦，以便能够灵活地扩充任一维度而对另一维度不造成任何影响。
 * (这里所谓地动态组合是指通过配置文件来指定地具体实现)。
 */
public class BaseCodeForm {

    //桥接模式有两个继承层次，一个是抽象继承层次，一个是实现层继承层次
    //这里是抽象继承层次

    public abstract class Pastel{
        protected Color color;

        public Pastel setColor(Color color) {
            this.color = color;
            return this;
        }

        public abstract void manufacture();
    }

    public class SmallPastel extends Pastel{

        @Override
        public void manufacture() {
            System.out.println("正在生产小号的彩色笔，颜色为:");
            color.tinting();
        }
    }

    public class MiddlePastel extends Pastel{

        @Override
        public void manufacture() {
            System.out.println("正在生产中号的彩色笔，颜色为");
            color.tinting();
        }
    }

    public class BigPastel extends Pastel{

        @Override
        public void manufacture() {
            System.out.println("正在生产大号的彩色笔，颜色为");
            color.tinting();
        }
    }
}

package com.designpattern.structural_pattern.flyweight_pattern;

import java.util.HashMap;

/**
 * 享元模式通过共享技术实现相同或相似对象的重用。
 * 享元对象能够做到共享的关键是区分了内部状态和外部状态
 *
 * 内部状态是存储在享元对象内部并且不会随环境改变而改变的状态，该状态可以共享
 * 外部状态是随环境改变而改变的、不可以共享的状态。通常由客户端保存，并在享元对象被创建后，需要使用的时候
 * 再传入到享元对象内部。
 *
 * 正因为区分了内部状态和外部状态，可以将具有相同内部状态的对象存储在享元池中，享元池中的对象是可以实现共享
 * 的，需要的时候就将对象从享元池中取出，实现对象的复用。通过向取出的对象注入不同的外部状态，可以得到一系列
 * 相似的对象。
 *
 * 享元模式有四个角色——抽象享元类、具体享元类、非共享享元类、享元工厂类
 */
public class BaseCodeForm {
    //这里演示带外部状态的享元模式

    /**
     * 外部状态类
     */
    public class Coordinates{
        private int x;
        private int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public Coordinates setX(int x) {
            this.x = x;
            return this;
        }

        public int getY() {
            return y;
        }

        public Coordinates setY(int y) {
            this.y = y;
            return this;
        }
    }

    /**
     * 抽象享元类
     */
    public abstract class IgoChessman{
        //内部状态——由具体的具体享元类提供
        public abstract String getColor();

        //外部状态，依赖外部状态类，在输出时再注入
        public void display(Coordinates coord){
            System.out.println("棋子颜色:"+this.getColor()+", 棋子位置:"+coord.getX()+","+coord.getY());
        }
    }

    /**
     * 具体享元类——黑色棋子
     */
    public class BlackIgoChessman extends IgoChessman{
        @Override
        public String getColor() {
            return "黑色";
        }
    }

    /**
     * 具体享元类——白色棋子
     */
    public class WhiteIgoChessman extends IgoChessman{

        @Override
        public String getColor() {
            return "白色";
        }
    }

}
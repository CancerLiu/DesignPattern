package com.designpattern.behavioral_pattern.strategy_pattern;

import com.designpattern.utils.PropertiesUtil;
import com.designpattern.utils.ReflectUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 *  策略模式——
 *  可以使用一种设计模式来实现灵活地选择解决途径，也能够方便地增加新的解决途径。
 *
 *  策略模式地主要目的是将算法的定义与使用分开。将算法的定义放在专门的策略类中，每一个策略类
 *  封装了一种实现算法。
 *
 *  策略模式有三个角色——环境类(使用算法的角色)、抽象策略类、具体策略类。
 *
 *  客户端使用时，只需向环境类注入一个具体的策略对象。
 *
 *  策略模式提供了一种可插入式算法的实现方案。
 *
 *  策略模式对应于解决某一问题的一个算法族，允许用户从该算法族中任选一个算法来解决某一问题，
 *  同时可以方便地更换算法或增加新的算法。
 */
public class BaseCodeFormAndClient {

    /**
     * 抽象策略类——折扣类
     */
    abstract class Discount{
        public abstract double calculate(double price);
    }

    /**
     * 具体策略类——学生打八折
     */
    public class  StudentDiscount extends Discount{

        @Override
        public double calculate(double price) {
            return price*0.8;
        }
    }
    /**
     * 具体策略类——儿童打八折
     */
    public class ChildDiscount extends Discount{

        @Override
        public double calculate(double price) {
            return price*0.1;
        }
    }

    /**
     * 环境类
     */
    class MovieTicket{
        private double price;
        private Discount discount;

        public double getPrice() {
            return discount.calculate(price);
        }

        public MovieTicket setPrice(double price) {
            this.price = price;
            return this;
        }

        public Discount getDiscount() {
            return discount;
        }

        public MovieTicket setDiscount(Discount discount) {
            this.discount = discount;
            return this;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String outerName = PropertiesUtil.getValue("strategy_pattern.outer.name");
        String innerName = PropertiesUtil.getValue("strategy_pattern.inner.name");

        //通过配置选择具体的算法
        BaseCodeFormAndClient.Discount discount = (BaseCodeFormAndClient.Discount)ReflectUtils.getInnerClass(outerName,innerName);
       double originalPrice =60.0;
        BaseCodeFormAndClient.MovieTicket ticket =new BaseCodeFormAndClient().new MovieTicket().setPrice(originalPrice).setDiscount(discount);

        System.out.println("原始票价为"+originalPrice+"最后的票价为:"+ticket.getPrice());
    }
}

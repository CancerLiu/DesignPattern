package com.designpattern.structural_pattern.adapter_pattern.object_adapter_pattern;

/**
 * 类适配器模式主要有三个角色，适配器、适配者(被适配者)和目标类。
 * 相当于适配器是适配者和目标类之间的桥梁
 *
 * 类似的变种还有双向适配器模式和缺省适配器模式
 */
public class BaseCodeForm {

    /**
     *  适配者A
     */
    public class AdapteeA{
        public void sort(){
            System.out.println("普通排序");
        }
    }

    /**
     * 适配者B
     */
    public class AdapteeB{
        public void quickSort(){
            System.out.println("快速排序");
        }
    }

    /**
     * 适配器
     */
    public class Adapter extends Target{
        private AdapteeA adapteeA;
        private AdapteeB adapteeB;

        public Adapter(){
            adapteeA = new BaseCodeForm().new AdapteeA();
            adapteeB = new BaseCodeForm().new AdapteeB();
        }

        @Override
        public void getFirstSortWay() {
            adapteeA.sort();
        }

        @Override
        public void getSecondSortWay() {
            adapteeB.quickSort();
        }
    }

    /**
     * 目标类
     */
    public abstract class Target{
        public abstract void getFirstSortWay();
        public abstract void getSecondSortWay();
    }
}

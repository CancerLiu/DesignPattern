package com.designpattern.structural_pattern.adapter_pattern.class_adapter_pattern;

/**
 * 类适配器模式主要有三个角色，适配器、适配者(被适配者)和目标类。
 * 相当于适配器是适配者和目标类之间的桥梁
 *
 * 相对于对象适配器，类适配器模式中的适配者和适配器是继承关系
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
     * 适配器
     */
    public class Adapter extends AdapteeA implements Target{

        @Override
        public void getFirstSortWay() {
            super.sort();
        }

    }

}

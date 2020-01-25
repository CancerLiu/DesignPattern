package com.designpattern.behavioral_pattern.iterator_pattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 迭代器模式——主要是将聚合类的遍历数据职责和存储数据职责进行了分离，将前者分理出了一个迭代器
 * 继承链。
 *
 * 迭代器模式有四个角色——抽象迭代器、具体迭代器、抽象聚合类和具体聚合类。
 *
 * 具体聚合类要依赖一个具体迭代器类，为第三方提供操作自己的方法;具体迭代器类要关联一个具体聚合类，
 * 来对之进行操作。
 *
 * 其中JDK中有内置的迭代器，这里以这个为实例说明。
 * 具体来说，JCF框架中的Collection接口扮演了抽象聚合类的一部分作用，
 * 另一部分作用(即返回其对应的具体迭代器类的作用交给了Iterable接口，该接口直接被Collection接口继承了)
 * Iterator接口扮演了抽象迭代器的作用，提供了具体具体迭代器的遍历方法。
 *
 * 同时JDK中还提供了双向遍历等功能，进一步的提供了便捷性，此处不再详述，具体请参见API。
 *
 */
public class BaseCodeFormAndClient {

    public static void main(String[] args) {
        List<String> list =new ArrayList<String>(){{
            add("liuchao");
            add("haha");
            add("xixi");
            add("niuniu");
        }};

        //由前面所知道，作为Collection的子类，其已经实现了抽象聚合类，通过相关方法得到
        //其对应的迭代器并使用
        Iterator iterator =list.iterator();
        while(iterator.hasNext()){
            String content=(String)iterator.next();
            System.out.println(content);
        }

    }




}

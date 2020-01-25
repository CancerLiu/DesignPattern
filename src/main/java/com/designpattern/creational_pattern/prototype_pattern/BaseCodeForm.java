package com.designpattern.creational_pattern.prototype_pattern;

/**
 * 因为Java语言的特殊性——提供了一个Cloneable接口(充当了抽象原型类)。所以此处直接使用
 * 这里直接实现深度复制，一步到位
 */
public class BaseCodeForm implements Cloneable {
    private int age;
    private String name;
    private Appendix appendix;

    public int getAge() {
        return age;
    }

    public BaseCodeForm setAge(int age) {
        this.age = age;
        return this;
    }

    public String getName() {
        return name;
    }

    public BaseCodeForm setName(String name) {
        this.name = name;
        return this;
    }

    public Appendix getAppendix() {
        return appendix;
    }

    public BaseCodeForm setAppendix(Appendix appendix) {
        this.appendix = appendix;
        return this;
    }

    @Override
    public BaseCodeForm clone() throws CloneNotSupportedException {
        return ((BaseCodeForm) super.clone()).setAppendix(this.appendix.clone());
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        BaseCodeForm form1 = new BaseCodeForm().setAge(28).setName("liuchao").setAppendix(new Appendix().setPrice(8.88));

        BaseCodeForm form2 = form1.clone();

        System.out.println(form1.appendix==form2.appendix);
    }
}

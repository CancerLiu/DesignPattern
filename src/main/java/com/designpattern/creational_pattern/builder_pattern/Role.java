package com.designpattern.creational_pattern.builder_pattern;

/**
 * 角色类，是建造的对象
 */
public class Role {
    private String name;
    private int age;
    private double height;
    private String simpleEvaluate;

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Role setAge(int age) {
        this.age = age;
        return this;
    }

    public double getHeight() {
        return height;
    }

    public Role setHeight(double height) {
        this.height = height;
        return this;
    }

    public String getSimpleEvaluate() {
        return simpleEvaluate;
    }

    public Role setSimpleEvaluate(String simpleEvaluate) {
        this.simpleEvaluate = simpleEvaluate;
        return this;
    }
}

package com.designpattern.creational_pattern.builder_pattern;

/**
 * 具体建造者A
 */
public class ConcreteRoleBuilderA extends AbstractRoleBuilder {
    public ConcreteRoleBuilderA() {
    }

    @Override
    public void buildName() {
        role.setName("佣兵");
    }

    @Override
    public void buildAge() {
        role.setAge(28);
    }

    @Override
    public void buildHeight() {
        role.setHeight(1.65);
    }

    @Override
    public void buildSimpleEvaluate() {
        role.setSimpleEvaluate("可以用双刀给对方造成巨大伤害");
    }
}

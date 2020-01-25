package com.designpattern.creational_pattern.builder_pattern;

/**
 * 具体建造者B
 */
public class ConcreteRoleBuilderB extends AbstractRoleBuilder {
    public ConcreteRoleBuilderB() {
    }

    @Override
    public void buildName() {
        role.setName("骑士");
    }

    @Override
    public void buildAge() {
        role.setAge(30);
    }

    @Override
    public void buildHeight() {
        role.setHeight(180);
    }

    @Override
    public void buildSimpleEvaluate() {
        role.setSimpleEvaluate("身穿盔甲，很平衡");
    }
}

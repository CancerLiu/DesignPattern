package com.designpattern.creational_pattern.builder_pattern;

/**
 * 抽象建造者
 */
public abstract class AbstractRoleBuilder {
    protected Role role = new Role();

    public abstract void buildName();

    public abstract void buildAge();

    public abstract void buildHeight();

    public abstract void buildSimpleEvaluate();

    public Role returnRole() {
        return role;
    }
}

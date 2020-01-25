package com.designpattern.creational_pattern.builder_pattern;

/**
 * 导演类，得到具体建造者对象，然后调用其具体建造方法来得到角色
 */
public class Director {

    public Role getRole(AbstractRoleBuilder builder) {
        builder.buildAge();
        builder.buildHeight();
        builder.buildSimpleEvaluate();
        builder.buildName();

        return builder.returnRole();
    }
}

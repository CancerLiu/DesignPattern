package com.designpattern.creational_pattern.builder_pattern;


import com.designpattern.utils.PropertiesUtil;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //针对抽象建造者编程
        AbstractRoleBuilder builder;
        String className = PropertiesUtil.getValue("builder_pattern.name");

        Class cls = Class.forName(className);
        builder = (AbstractRoleBuilder) cls.newInstance();

        //通过导演类来得到具体的角色(产品)
        Role role = new Director().getRole(builder);

        System.out.println(role.getName() + "     " + role.getAge() + "       " + role.getHeight() + "    " + role.getSimpleEvaluate());

    }
}

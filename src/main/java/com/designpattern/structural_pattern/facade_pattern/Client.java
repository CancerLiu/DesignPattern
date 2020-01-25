package com.designpattern.structural_pattern.facade_pattern;

import com.designpattern.utils.PropertiesUtil;
import com.designpattern.utils.ReflectUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String innerName= PropertiesUtil.getValue("facede_pattern.inner.name");
        String outerName=PropertiesUtil.getValue("facede_pattern.outer.name");

        BaseCodeForm.AbstractFacade facade=(BaseCodeForm.AbstractFacade)ReflectUtils.getInnerClass(outerName,innerName);
        facade.fileEncrypt("《三国演义》","D盘");

    }
}

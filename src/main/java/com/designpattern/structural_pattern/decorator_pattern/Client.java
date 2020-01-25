package com.designpattern.structural_pattern.decorator_pattern;

import com.designpattern.creational_pattern.singleton_pattern.BaseCodeForm;
import com.designpattern.utils.PropertiesUtil;
import com.designpattern.utils.ReflectUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
       String outerName= PropertiesUtil.getValue("decorator_pattern.outer.name");
       String decoratorName=PropertiesUtil.getValue("decorator_pattern.decorator.inner.name");
       String componentName=PropertiesUtil.getValue("decorator_pattern.component.inner.name");

       BaseFormCode.ComponentDecorator concreteDecorator=(BaseFormCode.ComponentDecorator)ReflectUtils.getInnerClass(outerName,decoratorName);
        BaseFormCode.Component concreteComponent=(BaseFormCode.Component)ReflectUtils.getInnerClass(outerName,componentName);

        concreteDecorator.setComponent(concreteComponent);
        concreteDecorator.display();
    }
}

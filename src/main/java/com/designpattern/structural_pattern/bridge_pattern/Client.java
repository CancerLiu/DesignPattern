package com.designpattern.structural_pattern.bridge_pattern;

import com.designpattern.utils.PropertiesUtil;
import com.designpattern.utils.ReflectUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String abstractInnerName = PropertiesUtil.getValue("bridge_pattern.abstract.inner.name");
        String abstractOutterName =PropertiesUtil.getValue("bridge_pattern.abstract.outter.name");
        String implementName = PropertiesUtil.getValue("bridge_pattern.implement.name");

       BaseCodeForm.Pastel pastel= (BaseCodeForm.Pastel)ReflectUtils.getInnerClass(abstractOutterName,abstractInnerName);

       Color color= (Color) ReflectUtils.getOutterClass(implementName);

       pastel.setColor(color);
       pastel.manufacture();
    }
}

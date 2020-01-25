package com.designpattern.structural_pattern.proxy_pattern.static_proxy_pattern;

import com.designpattern.utils.PropertiesUtil;
import com.designpattern.utils.ReflectUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String outerName = PropertiesUtil.getValue("static_proxy_pattern.outer.name");
        String innerProxyName = PropertiesUtil.getValue("static_proxy_pattern.inner.name");

        Search search =(Search) ReflectUtils.getInnerClass(outerName,innerProxyName);
        search.doSearch("sars","liuchao");
    }
}

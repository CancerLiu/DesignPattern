package com.designpattern.structural_pattern.adapter_pattern.object_adapter_pattern;

import com.designpattern.utils.PropertiesUtil;
import com.designpattern.utils.ReflectUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //通过配置得到适配器对象，然后通过其调用适配者对象中的相关方法
        String outterName=PropertiesUtil.getValue("outter.object_adapter_pattern.name");
        String innerName=PropertiesUtil.getValue("inner.object_adapter_pattern.name");

       BaseCodeForm.Target adapter= (BaseCodeForm.Adapter)ReflectUtils.getInnerClass(outterName,innerName);
        adapter.getFirstSortWay();
        adapter.getSecondSortWay();
    }
}

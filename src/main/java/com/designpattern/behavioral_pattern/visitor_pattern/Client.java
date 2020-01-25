package com.designpattern.behavioral_pattern.visitor_pattern;

import com.designpattern.utils.PropertiesUtil;
import com.designpattern.utils.ReflectUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        BaseCodeForm.EmployeeList lists = new BaseCodeForm().new EmployeeList();
        BaseCodeForm.Employee e1, e2, e3, p1, p2;

        e1 = new BaseCodeForm().new FulltimeEmployee("张无忌", 3200.00, 45);
        e2 = new BaseCodeForm().new FulltimeEmployee("杨过", 2000, 40);
        e3 = new BaseCodeForm().new FulltimeEmployee("段誉", 2400.00, 38);
        p1 = new BaseCodeForm().new ParttimeEmployee("洪七公", 80.00, 20);
        p2 = new BaseCodeForm().new ParttimeEmployee("郭靖", 60.00, 18);

        lists.addElement(e1);
        lists.addElement(e2);
        lists.addElement(e3);
        lists.addElement(p1);
        lists.addElement(p2);


        //通过配置引入具体访问类
        String outerName = PropertiesUtil.getValue("visitor_pattern.outer.name");
        String innerName = PropertiesUtil.getValue("visitor_pattern.inner.name");

        BaseCodeForm.Department department = (BaseCodeForm.Department) ReflectUtils.getInnerClass(outerName, innerName);
        lists.accept(department);

    }
}

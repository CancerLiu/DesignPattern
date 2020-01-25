package com.designpattern.creational_pattern.static_factory_method;


import com.designpattern.utils.PropertiesUtil;

import java.io.IOException;

public class FactoryAndClient {
    //之所以叫静态工厂方法，就在于提供给外部的方法是静态的
    //面向抽象编程，这里返回的是接口对象
    public static BaseCodeForm.Product getShape(String shape) {
        BaseCodeForm.Product product = null;

        if (shape.equalsIgnoreCase("circle")) {
            product = new BaseCodeForm().new Circle();
        } else if (shape.equalsIgnoreCase("rectangle")) {
            product = new BaseCodeForm().new Rectangle();
        } else {
            product = new BaseCodeForm().new triangle();
        }
        return product;
    }

    public static void main(String[] args) throws IOException {
        String shape = PropertiesUtil.getValue("shape.name");
        //创建对象
        BaseCodeForm.Product result = FactoryAndClient.getShape(shape);
        //使用对象
        result.display();
    }
}

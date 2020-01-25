package com.designpattern.behavioral_pattern.template_method_pattern;

import com.designpattern.behavioral_pattern.memento_pattern.BaseCodeForm;
import com.designpattern.utils.PropertiesUtil;
import com.designpattern.utils.ReflectUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * 模板模式——
 * 模板模式提供了一个模板方法来定义算法框架。而某些具体步骤的实现可以在其子类中完成。是一种
 * 基于继承的代码复用技术。
 *
 * 有两个角色——抽象类，其中定义了模板方法(具体按什么顺序来调用基本方法——算法的框架)和
 * 基本方法(具体的实现——在子类中实现);具体子类。
 *
 * 一个设计师负责给出一个算法的轮廓和框架，另一些设计师则负责给出这个算法的各个逻辑步骤。实现
 * 这些具体逻辑步骤的方法即为基本方法，而将这些基本方法汇总起来的方法即为模板方法。模板方法
 * 的名字也由此来。
 *
 * 父类(抽象模板类)中的方法一共可以有三类——抽象方法(等待子类实现)、普通方法(子类中通用的逻辑提到父类中)、
 * 钩子方法(表现为没有方法体内容的普通方法。主要作用可以用于条件判定)
 *
 */
public class BaseCodeFormAndClient {
    abstract class DataViewer {
        //读取数据
        protected abstract void readData();

        protected void convertToXml() {
            System.out.println("数据格式已转换为XML格式");
        }

        //展示数据
        protected abstract void display();

        //钩子方法——用于判断是否需要把数据转换为XML
        protected boolean isXML() {
            return false;
        }

        //模板方法
        protected void process() {
            readData();
            if (!isXML()) {
                convertToXml();
            }
            display();
        }
    }
        /**
         * 具体子类A
         */
      public   class SubDataViewerA extends DataViewer{

            @Override
            protected void readData() {
                System.out.println("从文件读取数据");
            }

            @Override
            protected void display() {
                System.out.println("在控制台显示");
            }

            @Override
            protected boolean isXML(){
                return false;
            }
        }
        /**
         * 具体子类A
         */
       public class SubDataViewerB extends DataViewer{

            @Override
            protected void readData() {
                System.out.println("从数据库读取数据");
            }

            @Override
            protected void display() {
                System.out.println("在屏幕上显示");
            }

            @Override
            protected boolean isXML(){
                return false;
            }
        }


    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String outerName = PropertiesUtil.getValue("template_pattern.outer.name");
        String innerName = PropertiesUtil.getValue("template_pattern.inner.name");

        BaseCodeFormAndClient.DataViewer viewer = (BaseCodeFormAndClient.DataViewer) ReflectUtils.getInnerClass(outerName,innerName);
        viewer.process();
    }

}

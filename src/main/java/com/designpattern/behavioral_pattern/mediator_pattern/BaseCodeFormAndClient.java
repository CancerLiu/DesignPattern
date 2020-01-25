package com.designpattern.behavioral_pattern.mediator_pattern;

import com.designpattern.utils.PropertiesUtil;
import com.designpattern.utils.ReflectUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * 中介者模式——
 * 引入一个中间类来协调类/对象之间的复杂关系，以降低系统的耦合度。
 *
 * 类似于数据库中多对多时建立中间表。
 *
 * 通过中介者模式，可以将对象的“网状结构”变成“星型接口”。
 *
 * 核心在于将原本网状结构中对象与对象之间的交互行为抽象分离出来，全部封装在中介类中进行统一管理协调。
 * 从而将一对多关系变为多对多关系。
 *
 * 主要有四个角色——
 * (1)抽象中介类——
 * (2)具体中介类——维持对各个同事的关联。
 * (3)抽象同事类——维持了一个对抽象同事类关联，便于其子类通过该关联来与中介者通信。
 * (4)具体同事类——具体需要先和中介者进行通信，然后通过其与各个具体同事类进行通信。
 *
 * 该模式主要的核心点在于中介者类的引入，中介者类主要承担了以下两个方面的作用——
 * (1)中转作用(结构性)——该功能主要通过抽象同事类对抽象中介类的关联关系来达到。
 * (2)协调作用(行为性)——中介者可以更进一步地对同事之间地关系进行封装。主要通过
 * 具体中介类对具体同事类地关联关系来达到。
 *
 * 如果需要增加具体同事类，则直接继承抽象同事类，然后再修改具体中介类或更换具体中介类
 * 如果要增加具体中介类，则直接更改具体中介类或继承抽象中介类或具体已有的具体中介类。
 *
 * 以下例子中，因为不同同事类的自身方法不同，所以中介者类没有针对抽象同事类编程。
 * 如果所有同事类方法相同，可以直接在抽象中介者中继承同事类的集合对象。
 * */
public class BaseCodeFormAndClient {

    /**
     * 抽象中介者类——提供同事类之间的交互方法
     */
    abstract class Media{
        public abstract void componetChanged(Component component);
    }

    /**
     * 抽象同事类——持有抽象中介者类，同时分别定义同事类的依赖方法(输入)和自身方法(输出),自身方法在子类中。
     */
    abstract class Component{
        private Media media;

        public Component setMedia(Media media) {
            this.media = media;
            return this;
        }

        //依赖方法()
        protected abstract void update();

        //依赖方法
        public void onChanged(){
            media.componetChanged(this);
        }
    }

    /**
     * 具体同事类A
     */
    class ConcreteComponentA extends Component{

        @Override
        protected void update() {
            System.out.println("被调用造成A");
        }
        //自身方法(输出方法)
        public void selected(){
            System.out.println("同事类A被调用");
        }
    }

    class ConcreteComponentB extends Component{
        @Override
        protected void update() {
            System.out.println("被调用造成B");
        }
        //自身方法(输出方法)
        public void selected(){
            System.out.println("同事类B被调用");
        }
    }

    class ConcreteComponentC extends Component{
        @Override
        protected void update() {
            System.out.println("被调用造成C");
        }
        //自身方法(输出方法)
        public void selected(){
            System.out.println("同事类C被调用");
        }
    }

    /**
     * 具体中介者类
     */
    class ConcreteMediator extends Media{
        //引入具体同事类
        private ConcreteComponentA concreteComponentA;
        private ConcreteComponentB concreteComponentB;
        private ConcreteComponentC concreteComponentC;

        public ConcreteMediator setConcreteComponentA(ConcreteComponentA concreteComponentA) {
            this.concreteComponentA = concreteComponentA;
            return this;
        }

        public ConcreteMediator setConcreteComponentB(ConcreteComponentB concreteComponentB) {
            this.concreteComponentB = concreteComponentB;
            return this;
        }

        public ConcreteMediator setConcreteComponentC(ConcreteComponentC concreteComponentC) {
            this.concreteComponentC = concreteComponentC;
            return this;
        }

        @Override
        public void componetChanged(Component component) {
            if(component==concreteComponentA){
                System.out.println("操作A同事类");
                concreteComponentB.update();
                concreteComponentC.update();
            }else if(component==concreteComponentB){
                System.out.println("操作B同事类");
                concreteComponentA.update();
                concreteComponentC.update();
            }else {
                System.out.println("操作C同事类");
                concreteComponentA.update();
                concreteComponentB.update();
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //通过反射找到具体中介者
        String outerName = PropertiesUtil.getValue("media_pattern.outer.name");
        String innerName=PropertiesUtil.getValue("media_pattern.inner.name");

        BaseCodeFormAndClient.ConcreteMediator media = (BaseCodeFormAndClient.ConcreteMediator) ReflectUtils.getInnerClass(outerName,innerName);

        //得到各个具体同事类
        BaseCodeFormAndClient.ConcreteComponentA concreteComponentA = new BaseCodeFormAndClient().new ConcreteComponentA();
        BaseCodeFormAndClient.ConcreteComponentB concreteComponentB = new BaseCodeFormAndClient().new ConcreteComponentB();
        BaseCodeFormAndClient.ConcreteComponentC concreteComponentC = new BaseCodeFormAndClient().new ConcreteComponentC();

        //具体同事类注册到具体中介者类中(中转作用)
        concreteComponentA.setMedia(media);
        concreteComponentB.setMedia(media);
        concreteComponentC.setMedia(media);

        //协调作用
        media.setConcreteComponentA(concreteComponentA)
                .setConcreteComponentB(concreteComponentB)
                .setConcreteComponentC(concreteComponentC);

        concreteComponentA.onChanged();

    }
}

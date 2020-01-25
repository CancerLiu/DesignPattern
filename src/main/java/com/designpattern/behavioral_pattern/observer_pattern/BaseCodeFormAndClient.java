package com.designpattern.behavioral_pattern.observer_pattern;

import com.designpattern.utils.PropertiesUtil;
import com.designpattern.utils.ReflectUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式(也被称为发布-订阅模式)——
 * 建立了一种对象与对象之间的依赖关系(大部分时候是一对多关系)。一个对象发生改变的时候，被注册
 * 的其他对象会产生对应的变换。
 * <p>
 * 有四个角色——目标类、具体目标类(具体目标类中的状态改变可能来自于第三方类，也可能就来自于具体观察者类)、
 * 观察者类和具体观察者类。
 * <p>
 * 更加复杂的情况下，具体观察者类的触发方法(如下面的beAttached()方法)会在执行时改变具体目标类的状态
 * 所以此时在具体目标类与具体观察者类之间还有关联或依赖关系。
 * 这种情况系统的扩展性将受到影响——增加新的具体目标类有时候需要修改原有观察者的代码(因为要新加入具体目标类);如果原有
 * 观察者类无须修改新增的具体目标，则系统扩展性不受影响。
 *
 * JDK中提供了Observer和Observable(其中集合使用的Vector...)类可以直接使用，原理一样，这里不再赘述。
 */
public class BaseCodeFormAndClient {

    /**
     * 抽象目标类
     */
    abstract class AbstractObject {
        protected List<AbstractObserver> lists = new ArrayList<>();
        protected String name;

        public void join(AbstractObserver observer) {
            lists.add(observer);
        }

        public void delete(AbstractObserver observer) {
            lists.remove(observer);
        }

        public String getName() {
            return name;
        }

        public AbstractObject setName(String name) {
            this.name = name;
            return this;
        }

        abstract void notifyAllObject(String name);
    }

    /**
     * 抽象观察者类
     */
    abstract class AbstractObserver {
        private String name;

        public AbstractObserver(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public AbstractObserver setName(String name) {
            this.name = name;
            return this;
        }

        public void help() {
            System.out.println(this.name + "正在前来支援！");
        }

        abstract void beAttached(AbstractObject object);
    }

    /**
     * 具体观察者——可以通过
     */
    class ConcreteObject extends AbstractObject {


        @Override
        void notifyAllObject(String name) {
            System.out.println(name + "请求支援");
            for (AbstractObserver obj : lists) {
                if (!(name.equalsIgnoreCase(obj.name))) {
                    obj.help();
                }
            }
        }
    }

    /**
     * 具体观察者A
     */
    class ConcreteObserverA extends AbstractObserver {


        public ConcreteObserverA(String name) {
            super(name);
        }

        @Override
        void beAttached(AbstractObject object) {
            System.out.println(this.getName() + "被攻击");
            object.notifyAllObject(this.getName());
        }
    }

    /**
     * 具体观察者A
     */
    class ConcreteObserverB extends AbstractObserver {


        public ConcreteObserverB(String name) {
            super(name);
        }

        @Override
        void beAttached(AbstractObject object) {
            System.out.println(this.getName() + "被攻击");
        }
    }

    /**
     * 具体观察者A
     */
    class ConcreteObserverC extends AbstractObserver {


        public ConcreteObserverC(String name) {
            super(name);
        }

        @Override
        void beAttached(AbstractObject object) {
            System.out.println(this.getName() + "被攻击");
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String outerName = PropertiesUtil.getValue("observer_pattern.outer.name");
        String innerName = PropertiesUtil.getValue("observer_pattern.inner.name");

        BaseCodeFormAndClient.AbstractObject o =(BaseCodeFormAndClient.AbstractObject)ReflectUtils.getInnerClass(outerName,innerName);

        //组建战队
        o.setName("动物园");
        BaseCodeFormAndClient.ConcreteObserverA tiger=new BaseCodeFormAndClient().new ConcreteObserverA("老虎");
        BaseCodeFormAndClient.ConcreteObserverB panda= new BaseCodeFormAndClient().new ConcreteObserverB("熊猫");
        BaseCodeFormAndClient.ConcreteObserverC elephant = new BaseCodeFormAndClient().new ConcreteObserverC("大象");
        o.join(tiger);
        o.join(panda);
        o.join(elephant);

        //某成员被攻击
        tiger.beAttached(o);
    }
}

package com.designpattern.behavioral_pattern.command_pattern;

import com.designpattern.utils.PropertiesUtil;
import com.designpattern.utils.ReflectUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * 命令模式——
 * 将请求的发送者与接收者进行解耦。发送者与接收者之间引入了新的命令对象，将发送者的请求封装在
 * 命令对象中，再通过命令对象来调用接收者的方法。
 *
 * 命令模式的核心在于引入一个命令类，通过命令类来降低发送者和接收者的耦合度，请求发送者只需要
 * 指定一个命令对象，再通过命令对象来调用请求接收者的处理方法。
 *
 * 命令的本质是对请求进行封装，一个请求对应一个命令，将发出命令的责任和执行命令的责任分割开。
 * 该模式允许请求的一方和接收的一方独立开来，使得请求的一方不必知道接收请求的一方的接口，更
 * 不必知道请求如何被接收、操作是否被执行、何时被执行以及怎么被执行。
 *
 * 命令模式有四个角色——调用者(也称请求发送者)、抽象命令者、具体命令者和命令接收者。
 *
 * 使用命令模式可以做如下的操作——请求排队(请求发送者需要持有抽象命令类的集合)、
 * 记录请求日志和做可撤销操作。还可以和组合模式一起组成宏命令。
 */
public class BaseCodeFormAndClient {

    //这里仅对最简单的命令模式样例做总结

    /**
     * 抽象命令者
     */
    abstract class AbstractCommand{
        abstract void execute();
    }

    /**
     * 具体命令者A
     */
    class CommandA extends AbstractCommand{

        private BaseCodeFormAndClient.ReceiverA receiverA;

        public CommandA() {
            this.receiverA =new BaseCodeFormAndClient().new ReceiverA();
        }

        @Override
        void execute() {
            receiverA.operateA();
        }
    }

    /**
     * 具体命令者B
     */
    class CommandB extends AbstractCommand{

        private BaseCodeFormAndClient.ReceiverB receiverB;

        public CommandB() {
            this.receiverB = new  BaseCodeFormAndClient().new ReceiverB();
        }

        @Override
        void execute() {
            receiverB.operateB();
        }
    }

    /**
     * 接收者A
     */
    class ReceiverA{
        public void operateA(){
            System.out.println("接受者A已经收到");
        }
    }

    /**
     * 接收者B
     */
    class ReceiverB{
        public void operateB(){
            System.out.println("接收者B已收到");
        }
    }

    /**
     * 调用者——消息发送者
     */
    class Invoker{
        private BaseCodeFormAndClient.AbstractCommand command;

        public Invoker setCommand(AbstractCommand command) {
            this.command = command;
            return this;
        }

        public void execute(){
            command.execute();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //这里主要就是两步，为请求发送者设置具体命令类
        String concreteCommandOuterName= PropertiesUtil.getValue("command_pattern.outer.name");
        String concreteCommandInnerName=PropertiesUtil.getValue("command_pattern.inner.name");

        BaseCodeFormAndClient.AbstractCommand command= (BaseCodeFormAndClient.AbstractCommand) ReflectUtils.getInnerClass(concreteCommandOuterName,concreteCommandInnerName);
        BaseCodeFormAndClient.Invoker invoker = new BaseCodeFormAndClient().new Invoker();

        invoker.setCommand(command);

        invoker.execute();

    }
}

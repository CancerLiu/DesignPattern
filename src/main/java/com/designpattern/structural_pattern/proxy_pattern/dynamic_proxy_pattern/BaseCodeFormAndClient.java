package com.designpattern.structural_pattern.proxy_pattern.dynamic_proxy_pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理可以让系统能够根据实际需要来动态创建代理类，让同一个代理类能够代理多个不同的真实主题类，
 * 而且可以代理不同的方法。
 */
public class BaseCodeFormAndClient {




    /**
     * 真实主题类——User分支
     */
    class UserDao implements AbstractUserDao{
        @Override
        public void findByUserId() {
            System.out.println("UserId分支");
        }
    }

    /**
     * 真实主题类——Name分支
     */
    class NameDao implements AbstractNameDao{
        @Override
        public void findByName() {
            System.out.println("Name分支");
        }
    }

    class DaoLoggerHandler implements InvocationHandler{
        private Long startTime;
        private Object target;

        public DaoLoggerHandler(){}

        public DaoLoggerHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            beforeInvoke();
            Object obj = method.invoke(target,args);
            afterInvoker();
            return obj;
        }

        private void beforeInvoke(){
            System.out.println("在方法之前调用");
            startTime = System.currentTimeMillis();
        }
        private void afterInvoker(){
            System.out.println("在方法之后调用");
            System.out.println("真实主题类方法调用时间为:"+(System.currentTimeMillis()-startTime));
        }
    }

    /**
     * 由这里的示例代码可知，所谓的动态主要是可以实时指定被代理的方法和实现的接口
     * 被代理的方法主要通过InvocationHandler类来指定，通过传入的真实主题类和指定相关的方法;
     * 需要实现的接口则通过Proxy的相关方法参数指定。
     * 通过上述两者最终来动态的得到最终的代理主题类，然后调用类实例的方法。
     *
     * @param args
     */
    public static void main(String[] args) {
        //得到抽象主题类实例
        AbstractUserDao userDao = new BaseCodeFormAndClient().new UserDao();
        //得到具体的"代理处理程序类"——可以将该类理解成真实主题类与代理主题类之间的桥梁
        InvocationHandler userHandler = new BaseCodeFormAndClient().new DaoLoggerHandler(userDao);

        //开始调用Proxy的相关方法得到代理主题类
        //该方法的参数依次为——代理类的类加载器、代理类所实现的接口列表、所指派的"调用处理程序类"
        AbstractUserDao userProxy =(AbstractUserDao) Proxy.newProxyInstance(AbstractUserDao.class.getClassLoader(),
                new Class[]{AbstractUserDao.class},userHandler);
        userProxy.findByUserId();

        System.out.println("————————————————————————————");
        //得到抽象主题类实例
        AbstractNameDao nameDao = new BaseCodeFormAndClient().new NameDao();
        //得到具体的"代理处理程序类"——可以将该类理解成真实主题类与代理主题类之间的桥梁
        InvocationHandler nameHandler = new BaseCodeFormAndClient().new DaoLoggerHandler(nameDao);

        //开始调用Proxy的相关方法得到代理主题类
        //该方法的参数依次为——代理类的类加载器、代理类所实现的接口列表、所指派的"调用处理程序类"
        AbstractNameDao nameProxy =(AbstractNameDao) Proxy.newProxyInstance(AbstractNameDao.class.getClassLoader(),
                new Class[]{AbstractNameDao.class},nameHandler);
        nameProxy.findByName();



    }
}

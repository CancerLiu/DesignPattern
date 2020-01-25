package com.designpattern.structural_pattern.proxy_pattern.static_proxy_pattern;

/**
 * 通常情况下，每一个代理类编译之后都会生成一个class文件，代理类所实现的接口和所代理的方法都被固定，
 * 这种代理被称之为静态代理。
 *
 * 代理模式一共有三个角色——抽象主题角色、代理主题角色和真实主题角色
 */
public class BaseCodeForm {

    class AccessValidator{
        public boolean validate(String userId){

            System.out.println("开始检测Id名为"+userId+"的用户是否有权限登陆");
            if(userId.toLowerCase().contains("s")){
                return true;
            }
            return false;
        }
    }

    class Logger{
        public void log(String userId){
            System.out.println("更新数据库Id为"+userId+"的数据");
        }
    }

    /**
     * 真实主题角色
     */
    class RealSearch implements Search{
        @Override
        public String doSearch(String userId, String keyWord) {
            System.out.println("Id为"+userId+"的用户使用关键字"+keyWord+"查询数据");
            return "查询得到的数据";
        }
    }

    /**
     * 代理主题角色
     */
    class ProxySearch implements Search{
        //组合进一个被代理类的实例，也可以通过方法聚合入。
        //类似的装饰模式的抽象装饰类也聚合进了一个抽象构件类实例
        private RealSearch realSearch =new BaseCodeForm().new RealSearch();

        private BaseCodeForm.AccessValidator validator;
        private BaseCodeForm.Logger logger;

        @Override
        public String doSearch(String userId, String keyWord) {
           if(validate(userId)) {
               logger(userId);
               realSearch.doSearch(userId, keyWord);
           }
            return null;
        }

        //如下封装具体代理所需要加入的功能方法
        private boolean validate(String userId){
            validator = new BaseCodeForm().new AccessValidator();
            return validator.validate(userId);
        }

        private void logger(String userId){
            logger = new BaseCodeForm().new Logger();
            logger.log(userId);
        }
    }

}

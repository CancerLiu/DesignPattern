package com.designpattern.behavioral_pattern.chain_of_responsibility_pattern;

import java.math.BigDecimal;

/**
 * 职责链模式专门用于处理请求链式传递的模式。
 *
 * 职责链可以是一条直线、一个环或者一个树形结构。
 *
 * 客户端无须关心请求的处理细节以及请求的传递，只需将请求发送到职责链上交给链上的一个个请求处理者即可。
 * 从而实现请求发送者和请求处理者解耦。
 *
 * 职责链有两个角色——抽象处理者和具体处理者。如果需要将请求对象封装，则还会多一个请求类
 *
 * 具体职责链模式分纯的和不纯的——
 * 前者要求一个具体处理者对象只能在两个行为中选择一个：要么承担全部责任，要不将责任推给下家；
 * 后者表示一个请求可能被多个具体处理者所处理。
 */
public class BaseCodeFormAndClient {

    /**
     * 请求类——采购单
     */
    class PurchaseRequest{
        //采购金额
        private BigDecimal amount;
        //采购项目名字
        private String name;


        public PurchaseRequest(BigDecimal amount, String name) {
            this.amount = amount;
            this.name = name;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public PurchaseRequest setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public String getName() {
            return name;
        }

        public PurchaseRequest setName(String name) {
            this.name = name;
            return this;
        }
    }

    /**
     * 抽象处理者——审批者
     */
    abstract class Approver{
        //作为对其下家的引用
        protected Approver approver;
        //审批者姓名
        protected String name;

        public Approver(String name) {
            this.name = name;
        }

        public Approver setApprover(Approver approver) {
            this.approver = approver;
            return this;
        }

        public abstract void processRequest(PurchaseRequest request);
    }

    /**
     * 具体处理类有两大作用——(1)处理请求;(2)转发请求
     *
     * 具体处理类——主任类
     */
    class Director extends Approver{

        public Director(String name) {
            super(name);
        }

        @Override
        public void processRequest(PurchaseRequest request) {
            if(request.getAmount().compareTo(new BigDecimal(1000))<=0){
                System.out.println("主任"+this.name+"审批"+request.getName()
                +"通过，金额为:"+request.getAmount());
            }else {
                //传递给下家
                this.approver.processRequest(request);
            }
        }
    }

    /**
     * 具体处理类有两大作用——(1)处理请求;(2)转发请求
     *
     * 具体处理类——副董事长类
     */
    class VicePresident extends Approver{

        public VicePresident(String name) {
            super(name);
        }

        @Override
        public void processRequest(PurchaseRequest request) {
            if(request.getAmount().compareTo(new BigDecimal(10000))<=0){
                System.out.println("副董事长"+this.name+"审批"+request.getName()
                        +"通过，金额为:"+request.getAmount());
            }else {
                //传递给下家
                this.approver.processRequest(request);
            }
        }
    }

    /**
     * 具体处理类有两大作用——(1)处理请求;(2)转发请求
     *
     * 具体处理类——董事长类
     */
    class President extends Approver{

        public President(String name) {
            super(name);
        }

        @Override
        public void processRequest(PurchaseRequest request) {
            if(request.getAmount().compareTo(new BigDecimal(100000))<=0){
                System.out.println("董事长"+this.name+"审批"+request.getName()
                        +"通过，金额为:"+request.getAmount());
            }else {
                //传递给下家
                this.approver.processRequest(request);
            }
        }
    }

    /**
     * 具体处理类有两大作用——(1)处理请求;(2)转发请求
     *
     * 具体处理类——董事会类
     */
    class Congress extends Approver{

        public Congress(String name) {
            super(name);
        }

        @Override
        public void processRequest(PurchaseRequest request) {
                System.out.println("董事会"+this.name+"审批"+request.getName()
                        +"通过，金额为:"+request.getAmount());
        }
    }

    public static void main(String[] args) {
        //和构建型中的组合模式一样，这里行为型中的职责链模式也是需要自己在客户端中组装职责链
        //这里可以使用配置文件的方式做到开闭原则，只是为了简便，所以在这里代码中直接写死。
        Approver zhangwj,yangg,guoj,dongsh;
        zhangwj = new BaseCodeFormAndClient().new Director("张无忌");
        yangg=new BaseCodeFormAndClient().new VicePresident("杨过");
        guoj=new BaseCodeFormAndClient().new President("郭靖");
        dongsh=new BaseCodeFormAndClient().new Congress("董事会");

        //组装责任链
        zhangwj.setApprover(yangg);
        yangg.setApprover(guoj);
        guoj.setApprover(dongsh);

        //创建不同条件的采购单并传入组装好的责任链中
        PurchaseRequest purchaseRequest1=new BaseCodeFormAndClient().new PurchaseRequest(new BigDecimal(900),"低价商品");
        zhangwj.processRequest(purchaseRequest1);

        PurchaseRequest purchaseRequest2=new BaseCodeFormAndClient().new PurchaseRequest(new BigDecimal(9000),"中价商品");
        zhangwj.processRequest(purchaseRequest2);

        PurchaseRequest purchaseRequest3=new BaseCodeFormAndClient().new PurchaseRequest(new BigDecimal(90000),"高价商品");
        zhangwj.processRequest(purchaseRequest3);
    }

}

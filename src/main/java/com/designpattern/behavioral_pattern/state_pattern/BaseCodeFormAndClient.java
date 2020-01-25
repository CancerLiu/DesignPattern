package com.designpattern.behavioral_pattern.state_pattern;

import java.math.BigDecimal;

/**
 * 状态模式——
 * 主要描述对象状态及其转换的模式。为了解决系统中复杂对象的状态转换以及不同状态下行为的
 * 封装行为。
 *
 * 在状态模式中，将对象在每一个状态下的行为和状态转移语句封装在一个个状态类中，通过这些状态类
 * 来分散冗长的条件转移语句，以此来让系统具有更好的灵活性和可扩展性。
 *
 * 状态模式将一个对象在不同状态下的不同行为封装在一个个状态类中，通过设置不同的状态对象可以让
 * 环境对象拥有不同的行为。而状态的转换细节对于客户端而言是透明的。
 *
 * 如果需要多个环境类实例共享同一个状态，则可以将该需要共享的状态设置为静态变量。
 *
 * 具体状态模式中的状态转换操作可以放在具体每个具体状态类中(实际还是调用了环境类的setState()方法)，
 * 也可以放在环境类中。
 *
 * 状态转换操作——
 * 放在环境类中时，环境类需要持有所有的具体状态对象，具体状态行为在具体状态类中;
 * 放在具体状态类中时，环境类可以关联抽象状态类。然后多态的引入具体的状态类。
 *
 * 总的来说，感觉如果状态变换很负责，状态之间的转换不是单向线性的，最好将状态转换放在具体状态类中。
 * 这里以第二种情况做示例。
 */
public class BaseCodeFormAndClient {

    /**
     * 账户类
     */
    class Account{
        private BigDecimal balance;
        private String owner;
        private AbstractState state;

        public Account(BigDecimal balance, String owner) {
            //初始化状态
            state = new BaseCodeFormAndClient().new NormalState().setAccount(this);
            this.balance = balance;
            this.owner = owner;
        }

        public void deposit(BigDecimal amount){
            System.out.println(this.owner+"开始存款,数额为:"+amount);
            state.deposit(amount);
            System.out.println("现在账户余额为:"+this.balance);
            System.out.println("现在账户状态为:"+this.state.getClass().getSimpleName());
            System.out.println("————————————————————————");
        }
        public void withdraw(BigDecimal amount){
            System.out.println(this.owner+"开始取款,数额为:"+amount);
            state.withdraw(amount);
            System.out.println("现在账户余额为:"+this.balance);
            System.out.println("现在账户状态为:"+this.state.getClass().getSimpleName());
            System.out.println("————————————————————————");
        }
        public void computeInterest(){
            state.computeInterest();
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public Account setBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public String getOwner() {
            return owner;
        }

        public Account setOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public AbstractState getState() {
            return state;
        }

        public Account setState(AbstractState state) {
            this.state = state;
            return this;
        }
    }

    /**
     * 抽象状态类
     */
    abstract class AbstractState{
        protected Account account;
        abstract void deposit(BigDecimal amount);
        abstract void withdraw(BigDecimal amount);
        abstract void computeInterest();
        abstract void stateCheck();
    }

    /**
     * 具体状态类——正常账户
     */
    class NormalState extends AbstractState{

        public NormalState setAccount(Account account){
            super.account=account;
            return this;
        }

        @Override
        void deposit(BigDecimal amount) {
            account.setBalance(account.getBalance().add(amount));
            stateCheck();
        }

        @Override
        void withdraw(BigDecimal amount) {
            account.setBalance(account.getBalance().add(amount.negate()));
            stateCheck();
        }

        @Override
        void computeInterest() {
            System.out.println("不需要支付利息");
        }

        @Override
        void stateCheck() {
            if(account.getBalance().compareTo(new BigDecimal(-2000))>0
                    &&account.getBalance().compareTo(BigDecimal.ZERO)<=0){
                account.setState(new OverdraftState().setAccount(account));
            }else if(account.getBalance().compareTo(new BigDecimal(2000))==0){
                account.setState(new RestrictedState().setAccount(account));
            }else if(account.getBalance().compareTo(new BigDecimal(-2000))<=0){
                System.out.println("操作受限");
                account.setState(new RestrictedState().setAccount(account));
            }
        }
    }

    /**
     * 具体状态类——受限账户
     */
    class RestrictedState extends AbstractState{

        public RestrictedState setAccount(Account account){
            super.account=account;
            return this;
        }

        @Override
        void deposit(BigDecimal amount) {
            account.setBalance(account.getBalance().add(amount));
            stateCheck();
        }

        @Override
        void withdraw(BigDecimal amount) {
            System.out.println("账户受限，不能操作");
        }

        @Override
        void computeInterest() {
            System.out.println("需要支付大量利息");
        }

        @Override
        void stateCheck() {
            if(account.getBalance().compareTo(BigDecimal.ZERO)>0){
                account.setState(new NormalState().setAccount(account));
            }else if(account.getBalance().compareTo(new BigDecimal(-2000))>0){
                account.setState(new OverdraftState().setAccount(account));
            }
        }
    }

    /**
     * 具体账户类——超支状态
     */
    class OverdraftState extends AbstractState{

        public OverdraftState setAccount(Account account){
            super.account=account;
            return this;
        }

        @Override
        void deposit(BigDecimal amount) {
            account.setBalance(account.getBalance().add(amount));
            stateCheck();
        }

        @Override
        void withdraw(BigDecimal amount) {
            account.setBalance(account.getBalance().add(amount.negate()));
            stateCheck();
        }

        @Override
        void computeInterest() {
            System.out.println("需要支付少量利息");
        }


        @Override
        void stateCheck() {
            if(account.getBalance().compareTo(BigDecimal.ZERO)>0){
                account.setState(new NormalState().setAccount(account));
            }else if(account.getBalance().compareTo(new BigDecimal(2000))==0){
                account.setState(new RestrictedState().setAccount(account));
            }else if(account.getBalance().compareTo(new BigDecimal(-2000))<0){
                System.out.println("操作受限");
                account.setState(new RestrictedState().setAccount(account));
            }
        }
    }


    public static void main(String[] args) {
        Account acc = new BaseCodeFormAndClient().new Account(BigDecimal.ZERO,"刘超");
        acc.deposit(new BigDecimal(1000));
        acc.withdraw(new BigDecimal(2000));
        acc.deposit(new BigDecimal(3000));
        acc.withdraw(new BigDecimal(4000));
        acc.computeInterest();
    }

}

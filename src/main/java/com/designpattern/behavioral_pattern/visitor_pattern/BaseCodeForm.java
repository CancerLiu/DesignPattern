package com.designpattern.behavioral_pattern.visitor_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式——
 * 该模式的出现的动机是——以不同的方式操作复杂对象结构。通俗说，就是不同访问者对复杂对象集合中的不同部分感兴趣，
 * 所以对不同部分进行操作。
 * 所谓的复杂对象结构在于，其集合中多态的集成了很多的对象。
 * <p>
 * 访问者模式较为复杂，通常包含访问者和被访问者两个主要组成部分。这些被访问的元素通常具有不同的类型，且不同的访问
 * 者可以对它们进行不同的访问操作。
 * <p>
 * 在使用该模式的时候，被访问元素通常不是单独存在的，它们存储在一个集合中，这个集合被称为"对象结构"，访问者通过
 * 遍历对象结构实现对其中存储的元素的逐个操作。
 * <p>
 * 主要有五个角色——抽象访问类(定义了每一种元素的单独访问方法)、具体访问类、抽象元素(定义了一个接受具体访问者
 * 并会在之后使用其相关方法访问相关元素的方法)、具体元素、对象结构(多态方式存储不同的元素对象)
 */
public class BaseCodeForm {

    /**
     * 抽象元素类——员工类
     */
    abstract class Employee {
        public abstract void accept(Department handler);
    }

    /**
     * 具体元素类——全职员工
     */
    class FulltimeEmployee extends Employee {
        private String name;
        private double weeklyWage;
        private int workTime;

        public FulltimeEmployee(String name, double weeklyWage, int workTime) {
            this.name = name;
            this.weeklyWage = weeklyWage;
            this.workTime = workTime;
        }

        public String getName() {
            return name;
        }

        public FulltimeEmployee setName(String name) {
            this.name = name;
            return this;
        }

        public double getWeeklyWage() {
            return weeklyWage;
        }

        public FulltimeEmployee setWeeklyWage(double weeklyWage) {
            this.weeklyWage = weeklyWage;
            return this;
        }

        public int getWorkTime() {
            return workTime;
        }

        public FulltimeEmployee setWorkTime(int workTime) {
            this.workTime = workTime;
            return this;
        }

        @Override
        public void accept(Department handler) {
            handler.visit(this);
        }
    }


    public class ParttimeEmployee extends Employee {
        private String name;
        private double hourWage;
        private int workTime;

        public ParttimeEmployee(String name, double hourWage, int workTime) {
            this.name = name;
            this.hourWage = hourWage;
            this.workTime = workTime;
        }

        public String getName() {
            return name;
        }

        public ParttimeEmployee setName(String name) {
            this.name = name;
            return this;
        }

        public double getHourWage() {
            return hourWage;
        }

        public ParttimeEmployee setWeeklyWage(double hourWage) {
            this.hourWage = hourWage;
            return this;
        }

        public int getWorkTime() {
            return workTime;
        }

        public ParttimeEmployee setWorkTime(int workTime) {
            this.workTime = workTime;
            return this;
        }

        @Override
        public void accept(Department handler) {
            handler.visit(this);
        }
    }

    /**
     * 抽象访问类——部门类
     */
    abstract class Department {
        public abstract void visit(FulltimeEmployee employee);

        public abstract void visit(ParttimeEmployee employee);
    }

    class FADDepartment extends Department {

        /**
         * 财务部对全职员工的访问
         */
        @Override
        public void visit(FulltimeEmployee employee) {
            int workTime = employee.getWorkTime();
            double weekWage = employee.getWeeklyWage();

            if (workTime > 40) {
                weekWage = weekWage + (workTime - 40) * 100;
            } else if (workTime < 40) {
                weekWage = weekWage - (40 - workTime) * 80;
            }
            System.out.println("正式员工" + employee.getName() + "实际工资为:" + weekWage + " 元");
        }

        /**
         * 财务部对兼职员工的访问
         */
        @Override
        public void visit(ParttimeEmployee employee) {
            int workTime = employee.getWorkTime();
            double hourWage = employee.getHourWage();

            System.out.println("临时工" + employee.getName() + "实际工资为:" + workTime * hourWage + " 元");
        }
    }

    class HRDDepartment extends Department {

        /**
         * 人力资源部对全职员工的访问
         */
        @Override
        public void visit(FulltimeEmployee employee) {
            int workTime = employee.getWorkTime();
            System.out.println("正式员工" + employee.getName() + "实际工作时间为:" + workTime + " 小时");
            if (workTime > 40) {
                System.out.println("正式员工" + employee.getName() + "加班工作时间为:" + (workTime - 40) + " 小时");
            } else if (workTime < 40) {
                System.out.println("正式员工" + employee.getName() + "请假工作时间为:" + (40 - workTime) + " 小时");
            }
        }

        /**
         * 人力资源部对兼职员工的访问
         */
        @Override
        public void visit(ParttimeEmployee employee) {
            int workTime = employee.getWorkTime();
            System.out.println("临时工"+employee.getName()+"实际工作时间为:"+workTime+" 小时");
        }
    }

    class EmployeeList{
        private List<Employee> list = new ArrayList<>();
        public void addElement(Employee employee){
            list.add(employee);
        }
        public void accept(Department handler){
            for(Object obj:list){
                ((BaseCodeForm.Employee)obj).accept(handler);
            }
        }
    }

}

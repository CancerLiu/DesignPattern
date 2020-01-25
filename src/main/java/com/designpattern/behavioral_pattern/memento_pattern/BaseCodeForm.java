package com.designpattern.behavioral_pattern.memento_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录模式——
 * 在不破坏封装的情况下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样可以在以后将对象
 * 恢复到原先保存的状态。
 * <p>
 * 备忘录有三个角色——
 * (1)原发器——用于创建修改备忘录类的状态。真实业务中一般是一个具体的业务类。
 * (2)备忘录类——存储原发器的状态。因为备忘录类保存的是原发器类的状态，所以其具体属性可以参考。
 * (3)负责人类——负责保存备忘录实例。其只负责存储对象，而不能修改对象，也无须知道对象的实现细节。
 * <p>
 * 这里以可以多次撤销的模型作为备忘录模式的示例
 *
 * 备忘录是一个很特殊的对象，只有原发器对它拥有控制的权力，负责人只负责管理备忘录，而其他类无法
 * 直接访问到备忘录。因此需要对备忘录进行封装。
 *
 * 如下例，如果undo操作之后，对修改了原发器的状态，则可能会产生分支等矛盾(联想到了git)。
 *
 * 也可以将备忘录类做成原发器类的内部类。
 */
public class BaseCodeForm {

    /**
     * 原发器类
     */
    public class Chessman {
        private String chessman;
        private int x;
        private int y;

        public Chessman(String chessman, int x, int y) {
            this.chessman = chessman;
            this.x = x;
            this.y = y;
        }

        public String getChessman() {
            return chessman;
        }

        public Chessman setChessman(String chessman) {
            this.chessman = chessman;
            return this;
        }

        public int getX() {
            return x;
        }

        public Chessman setX(int x) {
            this.x = x;
            return this;
        }

        public int getY() {
            return y;
        }

        public Chessman setY(int y) {
            this.y = y;
            return this;
        }

        //下面是最主要的，和备忘录类交互的方法
        public ChessmanMementor save(){
            return new BaseCodeForm().new ChessmanMementor(this.getChessman(),this.getX(),this.getY());
        }

        public void restore(ChessmanMementor mementor){
            this.chessman=mementor.getName();
            this.x=mementor.getX();
            this.y=mementor.getY();
        }
    }

    /**
     * 对应的其中一个备忘录类——一个备忘录模式是和一个原发器模式相对应的
     */
    public class ChessmanMementor {
        private String name;
        private int x;
        private int y;

        public ChessmanMementor(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        public String getName() {
            return name;
        }

        public ChessmanMementor setName(String name) {
            this.name = name;
            return this;
        }

        public int getX() {
            return x;
        }

        public ChessmanMementor setX(int x) {
            this.x = x;
            return this;
        }

        public int getY() {
            return y;
        }

        public ChessmanMementor setY(int y) {
            this.y = y;
            return this;
        }
    }

    /**
     * 负责人类——负责管理各个备忘录类
     */
    class Caretaker {
        private List<ChessmanMementor> lists = new ArrayList<>();

        public ChessmanMementor getLists(int index) {
           return lists.get(index);
        }

        public Caretaker setLists(ChessmanMementor chessman) {
            lists.add(chessman);
            return this;
        }
    }




}

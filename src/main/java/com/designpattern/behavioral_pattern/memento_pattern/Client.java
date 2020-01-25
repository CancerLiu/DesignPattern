package com.designpattern.behavioral_pattern.memento_pattern;

public class Client {

    private BaseCodeForm.Caretaker caretaker = new BaseCodeForm().new Caretaker();
    private int index = -1;

    private void play(BaseCodeForm.Chessman chessman) {
        System.out.println("棋子" + " " + chessman.getChessman() + " 在" + chessman.getX() + "  " + chessman.getY()
                + "落下");
        //保存状态
        caretaker.setLists(chessman.save());
        index++;
    }

    private void uodo(BaseCodeForm.Chessman chessman, int i) {
        System.out.println("————悔棋————");
        chessman.restore(caretaker.getLists(i - 1));
        index--;
        System.out.println("棋子" + " " + chessman.getChessman() + " 在" + chessman.getX() + "  " + chessman.getY()
                + "落下");
    }

    public static void main(String[] args) {
        BaseCodeForm.Chessman chessman = new BaseCodeForm().new Chessman("马", 1, 1);


        //在客户端定义具体的使用(这个示例中是没下一次棋就保存，没撤回一次则取消)
        Client client = new Client();
        //第一步
        client.play(chessman);
        //第二步
        chessman.setX(30);
        client.play(chessman);
        //第三步
        chessman.setY(8);
        client.play(chessman);
        //第四步——悔棋
        client.uodo(chessman,client.index);
    }
}

package com.designpattern.structural_pattern.flyweight_pattern;

public class Client {

    public static void main(String[] args) {
        BaseCodeForm.IgoChessman black1, white1, black2, white2;
        IgoChessmanFactory factory;

        //得到享元类工厂
        factory = IgoChessmanFactory.getInstance();

        //从工厂类中取得内部状态相同的享元对象
        black1 = factory.getIgoChessman("b");
        black2 = factory.getIgoChessman("b");
        white1 = factory.getIgoChessman("w");
        white2 = factory.getIgoChessman("w");

        System.out.println("判断两颗黑子是否相同:"+(black1==black2));
        System.out.println("判断两颗白子是否相同:"+(white1==white2));

        //最终打印的时候，输入外部状态
        black1.display(new BaseCodeForm().new Coordinates(2,4));
        black2.display(new BaseCodeForm().new Coordinates(3,8));
        white1.display(new BaseCodeForm().new Coordinates(55,89));
        white2.display(new BaseCodeForm().new Coordinates(44,1));
    }
}

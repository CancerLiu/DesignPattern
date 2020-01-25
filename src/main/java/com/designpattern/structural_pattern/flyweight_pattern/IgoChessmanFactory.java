package com.designpattern.structural_pattern.flyweight_pattern;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 享元工厂类——做成了单例模式
 */
public class IgoChessmanFactory {
    private static IgoChessmanFactory instance = new IgoChessmanFactory();

    private static ConcurrentHashMap<String, BaseCodeForm.IgoChessman> ht;

    private IgoChessmanFactory() {
        ht = new ConcurrentHashMap<>();
        BaseCodeForm.IgoChessman black=new BaseCodeForm().new BlackIgoChessman()
                ,white=new BaseCodeForm().new WhiteIgoChessman();
        ht.put("b",black);
        ht.put("w",white);
    }

    public static IgoChessmanFactory getInstance(){
        return instance;
    }

    public  BaseCodeForm.IgoChessman getIgoChessman(String color){
        return  ht.get(color);
    }
}

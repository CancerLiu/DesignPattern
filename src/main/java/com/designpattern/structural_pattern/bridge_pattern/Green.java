package com.designpattern.structural_pattern.bridge_pattern;

public class Green implements Color {
    @Override
    public void tinting() {
        System.out.println("绿色的");
    }
}

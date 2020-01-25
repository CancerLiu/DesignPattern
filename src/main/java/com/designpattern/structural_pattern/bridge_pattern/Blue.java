package com.designpattern.structural_pattern.bridge_pattern;

public class Blue implements Color {
    @Override
    public void tinting() {
        System.out.println("蓝色的");
    }
}

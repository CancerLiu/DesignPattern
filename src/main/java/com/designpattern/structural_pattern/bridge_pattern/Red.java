package com.designpattern.structural_pattern.bridge_pattern;

public class Red implements Color {
    @Override
    public void tinting() {
        System.out.println("红色的");
    }
}

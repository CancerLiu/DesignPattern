package com.designpattern.creational_pattern.prototype_pattern;

public class Appendix implements Cloneable {
    private double price;

    @Override
    protected Appendix clone() throws CloneNotSupportedException {
        return (Appendix) super.clone();
    }

    public double getPrice() {
        return price;
    }

    public Appendix setPrice(double price) {
        this.price = price;
        return this;
    }
}

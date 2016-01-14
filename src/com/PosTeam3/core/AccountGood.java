package com.PosTeam3.core;

import java.text.DecimalFormat;

/**
 * Created by 硕 on 2016/1/15.
 */
public class AccountGood {
    String name;
    String unit;
    double price;
    double count;
    double subtotal;
    double subTotBeforeDiscount;

    public double getSubTotBeforeDiscount() {
        return subTotBeforeDiscount;
    }

    public void setSubTotBeforeDiscount(double subTotBeforeDiscount) {
        this.subTotBeforeDiscount = subTotBeforeDiscount;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        return "名称 : " + name +
                ", 数量 : " + count + " " +unit+
                ", 单价 : " + df.format(price) + "(元)" +
                ", 小计 : " + df.format(subtotal) + "(元)";
    }
}

package com.PosTeam3.core;

/**
 * Created by 硕 on 2016/1/15.
 */
public class User {
    String no;
    String name;
    boolean isVip;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    @Override
    public String toString() {
        return "User{" +
                "用户=" + name + ' ' +
                ", 是否VIP=" + isVip +
                '}';
    }
}

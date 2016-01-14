package com.PosTeam3.shopcart;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by 硕 on 2016/1/15.
 */
public class ShopCartTest {

    @Test
    public void testRead() throws Exception {
        ShopCart shopCart = new ShopCart();
        Map<String, List<String>> map = shopCart.read("G:\\Learn\\GitHub\\Pos-Seed-03\\shopCart.txt");
        for(String key : map.keySet())
        {
            System.out.println(key);
        }
    }

    @Test
    public void testGroupByBarcode() throws Exception {

    }
}
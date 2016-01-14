package com.PosTeam3;

import com.PosTeam3.account.Account;
import com.PosTeam3.core.AccountGood;
import com.PosTeam3.core.Product;
import com.PosTeam3.index.Index;
import com.PosTeam3.shopcart.ShopCart;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;
import sun.org.mozilla.javascript.internal.json.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 硕 on 2016/1/11.
 */
public class Main {
    public static void main(String[] args) {
        ShopCart shopCart = new ShopCart();
        Index index = new Index();
        Account account = new Account();
        Map<String,Product> indexMap= index.read();
        List<String> shopCartList = shopCart.read("G:\\Learn\\GitHub\\Pos-Seed-03\\shopCart.txt");
        Map<String,Integer> groupByMap = shopCart.groupByBarcode(shopCartList);
        List<AccountGood> accountGoods = account.scan(groupByMap,indexMap);
        DecimalFormat df = new DecimalFormat("#0.00");
        System.out.println("***商店购物清单***");
        Date now = new Date();
        SimpleDateFormat time = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        System.out.println("打印时间 : "+time.format(now));

        for(AccountGood accountGood:accountGoods)
        {
            System.out.println(accountGood);
        }
        System.out.println("-----------------");
        System.out.println("总计 : "+df.format(account.account(accountGoods)));
        System.out.println("节省 : "+df.format(account.accountSaved(accountGoods)));
        System.out.println("*****************");

    }
}
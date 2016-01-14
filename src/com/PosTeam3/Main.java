package com.PosTeam3;

import com.PosTeam3.account.Account;
import com.PosTeam3.core.AccountGood;
import com.PosTeam3.core.Product;
import com.PosTeam3.core.User;
import com.PosTeam3.index.Index;
import com.PosTeam3.shopcart.ShopCart;
import com.PosTeam3.user.UserProcess;
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
import java.util.Iterator;
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
        UserProcess userProcess = new UserProcess();
        Map<String,Product> indexMap= index.read();
        Map<String, User> userMap = userProcess.loadUserMessage();
        Map<String,List<String>> shopCartMap = shopCart.read("G:\\Learn\\GitHub\\Pos-Seed-03\\shopCart.txt");

        for(String buyer:shopCartMap.keySet()) {
            Map<String, Integer> productMap = shopCart.groupByBarcode(shopCartMap.get(buyer));
            User user = userMap.get(buyer);

            List<AccountGood> accountGoods = account.scan(productMap, indexMap, user);

            DecimalFormat df = new DecimalFormat("#0.00");
            System.out.println("***商店购物清单***");
            account.getUserInfo(user,accountGoods);
            System.out.println("会员编号 : "+user.getNo()+"  会员积分 : "+user.getVipCount()+"分");
            System.out.println("-----------------");
            Date now = new Date();
            SimpleDateFormat time = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            System.out.println("打印时间 : " + time.format(now));

            for (AccountGood accountGood : accountGoods) {
                System.out.println(accountGood);
            }
            System.out.println("-----------------");
            for (AccountGood accountGood : accountGoods) {
                if (accountGood.isPromotion() && accountGood.getPromotionNum()!=0) {
                    System.out.println("赠送 : " + accountGood.getName() + " " + accountGood.getPromotionNum() + accountGood.getUnit());
                }
            }
            System.out.println("-----------------");
            System.out.println("总计 : " + df.format(account.account(accountGoods)));
            System.out.println("节省 : " + df.format(account.accountSaved(accountGoods)));
            System.out.println("*****************");
            userProcess.writeBack(userMap);
        }
    }
}
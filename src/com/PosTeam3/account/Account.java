package com.PosTeam3.account;

import com.PosTeam3.core.AccountGood;
import com.PosTeam3.core.Product;
import com.PosTeam3.core.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 硕 on 2016/1/15.
 */
public class Account {
    public List<AccountGood> scan(Map<String,Integer> shopCart, Map<String,Product> index, User user)
    {
        List<AccountGood> accountGoods = new ArrayList<AccountGood>();
        for(String shopCartKey : shopCart.keySet())
        {
            if(index.get(shopCartKey)!=null) {
                double vip = user.isVip() ? index.get(shopCartKey).getVipDiscount() : 1.0;
                AccountGood accountGood = new AccountGood();
                accountGood.setPrice(index.get(shopCartKey).getPrice());
                accountGood.setUnit(index.get(shopCartKey).getUnit());
                accountGood.setCount(shopCart.get(shopCartKey));
                accountGood.setName(index.get(shopCartKey).getName());
                accountGood.setSubtotal(index.get(shopCartKey).getDiscount()
                        * index.get(shopCartKey).getPrice()
                        * shopCart.get(shopCartKey)
                        * vip);
                accountGood.setSubTotBeforeDiscount(index.get(shopCartKey).getPrice()
                        * shopCart.get(shopCartKey));
                accountGood.setDiscount(index.get(shopCartKey).getDiscount());
                accountGood.setPromotion(index.get(shopCartKey).isPromotion());
                if(user.isVip()==false||index.get(shopCartKey).getVipDiscount()==1.0) {
                    if (accountGood.getDiscount() == 1.0) {
                        if (accountGood.isPromotion()) {
                            if (accountGood.getCount() % 3 == 2) {
                                accountGood.setSubtotal(accountGood.getSubtotal() - (accountGood.getCount() / 3) * accountGood.getPrice());
                                accountGood.setSubTotBeforeDiscount(accountGood.getSubTotBeforeDiscount() + accountGood.getPrice());
                                accountGood.setPromotionNum(accountGood.getCount() / 3 + 1);
                                accountGood.setCount(accountGood.getCount() + 1);
                            } else {
                                accountGood.setSubtotal(accountGood.getSubtotal() - (accountGood.getCount() / 3) * accountGood.getPrice());
                                accountGood.setPromotionNum(accountGood.getCount() / 3);
                            }
                        }
                    }
                }

                accountGoods.add(accountGood);
            }
        }
        return accountGoods;
    }

    public double account(List<AccountGood> accountGoods)
    {
        double sum = 0;
        for(AccountGood accountGood:accountGoods)
        {
            sum += accountGood.getSubtotal();
        }
        return sum;
    }
    public double accountSaved(List<AccountGood> accountGoods)
    {
        double sum = 0,sumBeforeSave = 0;
        for(AccountGood accountGood : accountGoods)
        {
            sum += accountGood.getSubtotal();
            sumBeforeSave += accountGood.getSubTotBeforeDiscount();
        }
        return sumBeforeSave-sum;
    }
}

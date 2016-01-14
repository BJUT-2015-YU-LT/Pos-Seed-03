package com.PosTeam3.account;

import com.PosTeam3.core.AccountGood;
import com.PosTeam3.core.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 硕 on 2016/1/15.
 */
public class Account {
    public List<AccountGood> scan(Map<String,Integer> shopCart, Map<String,Product> index)
    {
        List<AccountGood> accountGoods = new ArrayList<AccountGood>();
        for(String shopCartKey : shopCart.keySet())
        {
            if(index.get(shopCartKey)!=null)
            {
                AccountGood accountGood = new AccountGood();
                accountGood.setPrice(index.get(shopCartKey).getPrice());
                accountGood.setUnit(index.get(shopCartKey).getUnit());
                accountGood.setCount(shopCart.get(shopCartKey));
                accountGood.setName(index.get(shopCartKey).getName());
                accountGood.setSubtotal(index.get(shopCartKey).getDiscount()
                        *index.get(shopCartKey).getPrice()
                        *shopCart.get(shopCartKey));
                accountGood.setSubTotBeforeDiscount(index.get(shopCartKey).getPrice()
                        *shopCart.get(shopCartKey));
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

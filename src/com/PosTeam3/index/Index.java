package com.PosTeam3.index;

import com.PosTeam3.core.Product;
import net.sf.json.JSONObject;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 硕 on 2016/1/11.
 */
public class Index {
    public Map<String,Product> read()
    {
        Map<String,Product> map = new HashMap<String, Product>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("G:\\Learn\\GitHub\\Pos-Seed-03\\index.txt"));
            StringBuilder stringBuilder = new StringBuilder();
            String data = new String();
            do {
                data = bufferedReader.readLine();
                if (data != null) {
                    stringBuilder.append(data);
                }
            } while (data != null);
            JSONObject jsonObject = JSONObject.fromObject(stringBuilder.toString());
            Iterator it = jsonObject.keys();
            while (it.hasNext())
            {
                String key = it.next().toString();
                JSONObject childObject = jsonObject.getJSONObject(key);
                String name = childObject.getString("name");
                String unit = childObject.getString("unit");
                double price = childObject.getDouble("price");
                double discount = childObject.getDouble("discount");
                boolean promotion = childObject.getBoolean("promotion");
                Product product = new Product();
                product.setBarcode(key);
                product.setName(name); product.setUnit(unit);
                product.setPrice(price); product.setDiscount(discount);
                product.setPromotion(promotion);
                map.put(key, product);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return map;
    }


}

package com.PosTeam3.shopcart;

import com.PosTeam3.core.Product;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by 硕 on 2016/1/11.
 */
public class ShopCart {
    public Map<String,List<String>> read(String filePath)
    {
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
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
            while(it.hasNext())
            {
                List<String> itemList = new ArrayList<String>();
                String key = it.next().toString();
                String username = jsonObject.getString("user");
                JSONArray jsonChildArray = jsonObject.getJSONArray("items");
                int size = jsonChildArray.length();
                for(int i = 0; i<size ;i++)
                {

                    itemList.add(jsonChildArray.getString(i));
                }
                map.put(username,itemList);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return map;
    }

    public Map<String,Integer> groupByBarcode(List<String> list)
    {
        Map<String,Integer> map = new HashMap<String,Integer>();
        Iterator it = list.iterator();
        while (it.hasNext())
        {
            String key = (String)it.next();
            if(map.get(key)==null)
            {
                map.put(key,1);
            }
            else
            {
                int n = map.get(key);
                n++;
                map.put(key,n++);
            }
        }
        return map;
    }
}

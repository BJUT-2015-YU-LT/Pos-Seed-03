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
    public List<String> read(String filePath)
    {
        List<String> list = new ArrayList<String>();
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
            JSONArray jsonArray = JSONArray.fromObject(stringBuilder.toString());
            for(int i=0;i<jsonArray.length();i++)
            {
                list.add(jsonArray.getString(i));
            }
            Collections.sort(list, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return list;
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

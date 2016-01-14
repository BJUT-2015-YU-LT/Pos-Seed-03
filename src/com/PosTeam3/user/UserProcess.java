package com.PosTeam3.user;

import com.PosTeam3.core.Product;
import com.PosTeam3.core.User;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 硕 on 2016/1/15.
 */
public class UserProcess {
    public Map<String,User> loadUserMessage()
    {
        Map<String,User> map = new HashMap<String,User>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("G:\\Learn\\GitHub\\Pos-Seed-03\\user.txt"));
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
                User user = new User();
                user.setNo(key);
                user.setName(childObject.getString("name"));
                user.setVip(childObject.getBoolean("isVip"));
                map.put(key,user);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return map;
    }
}

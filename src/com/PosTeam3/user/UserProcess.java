package com.PosTeam3.user;

import com.PosTeam3.core.Product;
import com.PosTeam3.core.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
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
                user.setVipCount(childObject.getInt("vipCount"));
                map.put(key,user);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return map;
    }

    public void writeBack(Map<String, User> map)
    {
        JSONObject jsonObject = new JSONObject();
        for(String key:map.keySet())
        {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("name",map.get(key).getName());
            jsonObject1.put("isVip",map.get(key).isVip());
            jsonObject1.put("vipCount",map.get(key).getVipCount());
            jsonObject.put(key,jsonObject1);
        }

        try{
            File file =new File("G:\\Learn\\GitHub\\Pos-Seed-03\\user.txt");

            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter fileWritter = new FileWriter(file.getName());
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(jsonObject.toString());
            bufferWritter.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

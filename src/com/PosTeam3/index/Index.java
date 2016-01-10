package com.PosTeam3.index;

import com.PosTeam3.core.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by 硕 on 2016/1/11.
 */
public class Index {
    List<Product> read()
    {
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("G:\\Learn\\GitHub\\Pos-Seed-03\\index.txt"));
            StringBuilder stringBuilder = new StringBuilder();
            String data = null;
            do {
                data = bufferedReader.readLine();
                if (data != null) {
                    stringBuilder.append(data);
                }
            } while (data != null);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

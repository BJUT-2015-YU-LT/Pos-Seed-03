package com.PosTeam3.index;

import com.PosTeam3.core.Product;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by 硕 on 2016/1/15.
 */
public class IndexTest {

    @Test
    public void testRead() throws Exception {
        Index index = new Index();
        Map<String,Product> indexMap= index.read();
        for(String key : indexMap.keySet())
        {
            System.out.println(key);
        }
    }
}
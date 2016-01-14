package com.PosTeam3.user;

import com.PosTeam3.core.User;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by 硕 on 2016/1/15.
 */
public class UserProcessTest {

    @Test
    public void testLoadUserMessage() throws Exception {
        UserProcess userProcess = new UserProcess();
        Map<String,User> map = userProcess.loadUserMessage();
        for(String key:map.keySet())
        {
            User user = map.get(key);
            System.out.println(user);
            System.out.println(user.getVipCount());
        }
    }
}
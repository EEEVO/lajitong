package com.auts.lajitong.util;

import java.util.UUID;

public class UserIdGenerator {

    public static String gen(){
        return UUID.randomUUID().toString() + "-" + System.currentTimeMillis()/1000;
    }
}

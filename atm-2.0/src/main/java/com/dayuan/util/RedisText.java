package com.dayuan.util;

import redis.clients.jedis.Jedis;

public class RedisText {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.2.171");
        jedis.set("foo", "bar");
        String foo = jedis.get("foo");
        System.out.println(foo);
    }
}

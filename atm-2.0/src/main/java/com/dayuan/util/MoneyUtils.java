package com.dayuan.util;

import java.math.BigDecimal;

public class MoneyUtils {
    public static String sum(String a , String b){
        BigDecimal b1 = new BigDecimal(a);
        BigDecimal b2 = new BigDecimal(b);
        return b1.add(b2).setScale(2,BigDecimal.ROUND_HALF_EVEN).toString();
    }

    public static String sub(String a , String b){
        BigDecimal a1 = new BigDecimal(a);
        BigDecimal b1 = new BigDecimal(b);

        return a1.subtract(b1).setScale(2,BigDecimal.ROUND_HALF_EVEN).toString();

    }
}

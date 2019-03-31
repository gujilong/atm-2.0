package com.dayuan.util;

import java.util.Random;

public class RandomCardNo {
    public static String cardNo(){
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<5;i++){
           builder.append(Math.abs(random.nextInt()%10)) ;
        }


        return builder.toString();
    }

    public static void main(String[] args) {

        System.out.println(cardNo());
    }

}

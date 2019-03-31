package com.dayuan.util;

public class Text {
    public static void main(String[] args) {



        while(true){

//           new Thread(new Runnable() {
//               @Override
//               public void run() {
//
//               }
//           }).start();

            bbb();

        }

    }

    public static void aaa(){
        bbb();
    }

    public static void bbb(){
        aaa();
    }
}

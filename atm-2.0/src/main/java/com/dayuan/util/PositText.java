package com.dayuan.util;

import com.dayuan.service.BankCardService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class PositText {
    static final  Integer total = 5000;
    static   Integer success = 0;
    static   Integer err = 0;

    static AtomicInteger success_num= new AtomicInteger(0);
    static AtomicInteger err_num= new AtomicInteger(0);

    public static CyclicBarrier cyclicBarrier= new CyclicBarrier(total);
    public static CountDownLatch countDownLatch= new CountDownLatch(total);

    public static void main(String[] args) throws InterruptedException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/spring.xml");
        BankCardService bankCardService = context.getBean(BankCardService.class);
        MyDepositRunable myDepositRunable = new MyDepositRunable(bankCardService);
        for (int i =0 ;i<=total;i++){
            new Thread(myDepositRunable).start();
        }
        countDownLatch.await();
        System.out.println("成功:"+success+",失败:"+err);
        System.out.println("成功:"+success_num+",失败:"+err_num);
    }
}


class MyDepositRunable implements Runnable{
    public BankCardService bankCardService;
    public MyDepositRunable(BankCardService bankCardService){
        this.bankCardService=bankCardService;
    }

    @Override
    public void run() {
        try {
            PositText.cyclicBarrier.await();
            bankCardService.depositleguan("87295","1");
            //bankCardService.deposit("87295","1");
            PositText.success ++;
            PositText.success_num.getAndAdd(1);
        } catch (Exception e) {
            e.printStackTrace();
            PositText.err ++;
            PositText.err_num.getAndAdd(1);
        }
        PositText.countDownLatch.countDown();
    }
}
package com.dayuan.task;


import com.dayuan.entity.Transfer;
import com.dayuan.mapper.TransferMapper;
import com.dayuan.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component("transferTask")
public class TransferTask {

    @Autowired
    TransferMapper transferMapper;
    @Autowired
    BankCardService bankCardService;

    public void doTask() {
        System.out.println("转账任务 do com.dayuan.task ...");
        processTransfer();
    }
    private void processTransfer() {

            int currpag = 1;
            int query = 5;
            int offset = (currpag - 1)*query;
            List<Transfer> transfers =null;
            do{
                transfers = transferMapper.seByStatus("0",offset,query);
                for(Transfer transfer:transfers){
                    try{
                    bankCardService.transferin(transfer.getInCardNo(),transfer.getStatus(),transfer.getMoney(),transfer.getId());

                    }catch (Exception e){
                    transferMapper.updateStatus(transfer.getId(),transfer.getStatus(),"2",new Date());
                    e.printStackTrace();
                }

            }
                currpag++;
            }while (transfers.size()>0);






    }
}

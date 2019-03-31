package com.dayuan.task;

import com.dayuan.entity.Transfer;
import com.dayuan.mapper.TransferMapper;
import com.dayuan.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("transferCancelTask")
public class TransferCancelTask {
    @Autowired
    TransferMapper transferMapper;
    @Autowired
    BankCardService bankCardService;

    public void doTask(){
        transferCancel();
    }



    public void transferCancel(){
        int currpag = 1;
        int query = 5;
        int offset = (currpag - 1)*query;
        List<Transfer> transfers = null;


        do {
            transfers = transferMapper.seByStatus("2", offset, 5);
            for(Transfer transfer:transfers){
                bankCardService.transferCancel(transfer);
            }
            currpag++;

        }while (transfers.size()>0);
    }
}

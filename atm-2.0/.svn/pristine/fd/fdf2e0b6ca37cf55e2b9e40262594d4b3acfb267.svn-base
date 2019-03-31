package com.dayuan.controller;

import com.dayuan.dto.CardDto;
import com.dayuan.dto.FlowDto;
import com.dayuan.dto.ResponseDTO;
import com.dayuan.exception.ExceptionDispose;
import com.dayuan.service.BankCardService;
import com.dayuan.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/card")
public class BankCardController extends ExceptionDispose {

    @Autowired
    BankCardService bankCardService;

    //    开户
    @RequestMapping(value = "/openAccount")
    @ResponseBody
    public ResponseDTO openAccount(String pwd , String conPwd, HttpSession session){

        String cardNo = bankCardService.openAccount(pwd, conPwd, UserId.getUserId(session));
        return ResponseDTO.success(cardNo);
    }


    //存款
    @RequestMapping(value = "/deposit" , method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO deposit(String cardNo , String money){
        bankCardService.deposit(cardNo,money);
        return ResponseDTO.success();

    }



    //取款
    @RequestMapping(value = "/todraw",method =RequestMethod.POST)
    @ResponseBody
    public ResponseDTO todraw(String money,String cardNo ,String pwd){
        bankCardService.todraw(money,cardNo,pwd);
        return ResponseDTO.success();
    }



    //转账
    @RequestMapping(value = "/transfer",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO transfer(String inCardNo,String outCardNo,String pwd,String money,HttpSession session){
        bankCardService.transferlate(inCardNo,outCardNo,pwd,money,UserId.getUserId(session));
        return ResponseDTO.success();

    }

    //查卡号
    @RequestMapping(value = "/findcardNo",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO findcardNo(HttpSession session){
        List<String> cards = bankCardService.findCardNoByUserid(UserId.getUserId(session));
        for(String card :cards){
            System.out.println(card);
        }
        return ResponseDTO.success(cards);
    }

    //流水
    @RequestMapping(value = "/qflow",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO qflow(String cardNo,String pwd,
                             @RequestParam(value = "currentPageindex",defaultValue = "1")Integer currentPageindex){

        PageUtils pageUtils = bankCardService.qflow(cardNo, pwd, currentPageindex);

        return ResponseDTO.success(pageUtils);
    }

    //最近10笔流水
    @RequestMapping(value = "/qTenflow",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO qTenflow(HttpSession session){

        ArrayList<FlowDto> flows = bankCardService.qTenflow(UserId.getUserId(session));
        return ResponseDTO.success(flows);
    }


    //查银行卡信息
    @RequestMapping(value = "/cardInf",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO cardInf(HttpSession session){

        ArrayList<CardDto> cards = bankCardService.cardInf(UserId.getUserId(session));
        return ResponseDTO.success(cards);
    }




    //下载流水
    @RequestMapping(value = "/downloadFlows")
    @ResponseBody
    public void downloadFlows(String cardNo, String pwd, HttpServletResponse response){
        int currentPageindex = 1;
        PageUtils pageUtils = null;
        response.setContentType("application/octet-stream; charset=gbk");
        //文件名如果有中文 通过URLEncoder.encode(filename, "UTF-8")转码
        response.setHeader("content-disposition", "attachment;filename=" + cardNo + ".csv");

        try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(),"gbk"));){

            bufferedWriter.write("卡号,金额,备注,时间");
            bufferedWriter.newLine();
            bufferedWriter.flush();

            do{
                pageUtils = bankCardService.qflow(cardNo, pwd, currentPageindex);
                List<FlowDto> datas =(List<FlowDto> ) pageUtils.getData();
                for (FlowDto data :datas){
                    String f= data.getCardNum()+","+data.getAmount()+","+data.getDesc()+","+data.getCreateTime();
                    bufferedWriter.write(f);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                currentPageindex++;

            }while (pageUtils.getTotalPages()!=0 && currentPageindex<=pageUtils.getTotalPages());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

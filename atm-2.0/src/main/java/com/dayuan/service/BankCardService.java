package com.dayuan.service;

import com.dayuan.dto.CardDto;
import com.dayuan.dto.FlowDto;
import com.dayuan.entity.Card;
import com.dayuan.entity.Flow;
import com.dayuan.entity.Transfer;
import com.dayuan.exception.BizException;
import com.dayuan.mapper.CardMapper;
import com.dayuan.mapper.FlowMapper;
import com.dayuan.mapper.TransferMapper;
import com.dayuan.util.DateUtil;
import com.dayuan.util.MoneyUtils;
import com.dayuan.util.PageUtils;
import com.dayuan.util.RandomCardNo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BankCardService {
    @Resource
    CardMapper cardMapper;
    @Resource
    FlowMapper flowMapper;
    @Autowired
    TransferMapper transferMapper;


    //开户
    public String openAccount(String pwd, String conPwd, int userId){

        if(StringUtils.isBlank(pwd)){
           throw  new BizException("请输入密码");
        }

        if(StringUtils.isBlank(conPwd)){
            throw  new BizException("请输入密码");
        }

        if(!pwd.equals(conPwd)){
            throw new BizException("两次密码输入不一致");
        }
        //生成随机卡号
        String cardNo = RandomCardNo.cardNo();
        Card i = cardMapper.seByC(cardNo);
        if(i!=null){
            throw new BizException("卡号已存在,请重新开户");
        }

        Card card = new Card();
        card.setBalance("0.00");
        card.setCardNum(cardNo);
        card.setCreateTime(new Date());
        card.setModifyTime(new Date());
        card.setPwd(pwd);
        //银行卡状态 1-正常  2-注销
        card.setStatus((byte)1);
        card.setUserId(userId);
        card.setVersion(0);

        int rows = cardMapper.insert(card);
        if (rows !=1){
            throw new BizException("开户失败");
        }
        return cardNo;
    }



    //存款
    @Transactional(rollbackFor = Exception.class)
    public void deposit(String cardNo , String money){
        //   校验参数  cardNo money

        if(StringUtils.isBlank(cardNo)){
            throw new BizException("账户或密码不正确");
        }

        if (StringUtils.isBlank(money)){
            throw new BizException("请输入存款金额");
        }

        double v = Double.parseDouble(money);
        if (v<=0){
            throw new BizException("请输入存款金额");
        }


        //查询账户
        Card card = cardMapper.seByCUpData(cardNo);
        if(card==null){
            throw new BizException("账户不存在");
        }


        String balance = card.getBalance();
        String sum = MoneyUtils.sum(balance, money);
        card.setBalance(sum);
        int i = cardMapper.updateByPrimaryKeySelective(card);

        if(i !=1){
            throw new BizException("存款失败,请联系客服");
        }

        //插入流水
        Flow flow = new Flow();
        flow.setCardId(card.getId());
        flow.setAmount(money);
        flow.setCardNum(card.getCardNum());
        flow.setCreateTime(new Date());
        flow.setFlowDesc("存钱");//流水类型 1-存钱 2-取钱 3-转账支出 4-转账收入
        flow.setFlowType((byte)1);
        flow.setUserId(card.getUserId());
        flowMapper.insertSelective(flow);
    }




    @Transactional(rollbackFor = Exception.class)
    public void depositleguan(String cardNo , String money){
        //   校验参数  cardNo money

        if(StringUtils.isBlank(cardNo)){
            throw new BizException("账户或密码不正确");
        }

        if (StringUtils.isBlank(money)){
            throw new BizException("请输入存款金额");
        }

        double v = Double.parseDouble(money);
        if (v<=0){
            throw new BizException("请输入存款金额");
        }


        //查询账户
        Card card = cardMapper.seByC(cardNo);
        if(card==null){
            throw new BizException("账户不存在");
        }


        String balance = card.getBalance();
        String sum = MoneyUtils.sum(balance, money);
        card.setBalance(sum);
        int i = cardMapper.upBalance(card.getCardNum(), card.getBalance(), card.getVersion());
        if(i !=1){
            throw new BizException("存款失败,请联系客服");
        }

        //插入流水
//        Flow flow = new Flow();
//        flow.setCardId(card.getId());
//        flow.setAmount(money);
//        flow.setCardNum(card.getCardNum());
//        flow.setCreateTime(new Date());
//        flow.setFlowDesc("存钱");//流水类型 1-存钱 2-取钱 3-转账支出 4-转账收入
//        flow.setFlowType((byte)1);
//        flow.setUserId(card.getUserId());
//        flowMapper.insertSelective(flow);
    }


    //取款
    @Transactional(rollbackFor = Exception.class)
    public void todraw(String money,String cardNo ,String pwd){


        //  2018/7/19 校验参数 cardNo pwd money
        if(StringUtils.isBlank(cardNo)||StringUtils.isBlank(pwd)||StringUtils.isBlank(money)){
            throw new BizException("请输入必填参数");
        }

        double v = Double.parseDouble(money);
        if(v<0){
            throw new BizException("请输入正确的取款金额");
        }

        Card card = cardMapper.seByC(cardNo);
        if (card == null||!card.getPwd().equals(pwd)){
            throw new BizException("账号或密码错误");
        }

        // 2018/7/19 校验账户余额是否足够


        String sub = MoneyUtils.sub(card.getBalance(), money);
        double v1 = Double.parseDouble(sub);
        if (v1<0){
            throw new BizException("账户余额不足");
        }

        // 2018/7/19 更新余额
        int i = cardMapper.upBalance(cardNo, sub, card.getVersion());
        if(i!=1){
            throw new BizException("取款失败");
        }

        //  2018/7/19 插入流水
        //插入流水
        Flow flow = new Flow();
        flow.setCardId(card.getId());
        flow.setAmount(money);
        flow.setCardNum(card.getCardNum());
        flow.setCreateTime(new Date());
        flow.setFlowDesc("取钱");//流水类型 1-存钱 2-取钱 3-转账支出 4-转账收入
        flow.setFlowType((byte)2);
        flow.setUserId(card.getUserId());
        flowMapper.insertSelective(flow);
    }


    //转账
    @Transactional(rollbackFor = Exception.class)
    public void transfer(String inCardNo,String outCardNo,String pwd,String money,Integer userId){

        //参数检验
        if(StringUtils.isBlank(inCardNo)||StringUtils.isBlank(outCardNo)
                ||StringUtils.isBlank(pwd) ||StringUtils.isBlank(money)){

            throw new BizException("请填写必填参数");
        }

        double v = Double.parseDouble(money);
        if (v<=0){
            throw new BizException("转账金额不能小于0");
        }

        if (userId == null){
            throw new BizException("请先登陆");
        }


        //效验账户密码
        Card card = cardMapper.seByC(outCardNo);
        if (card == null||!card.getPwd().equals(pwd)){
            throw new BizException("账号或密码错误");
        }



        Card inCard = cardMapper.seByC(inCardNo);
        Card outCard = cardMapper.seByC(outCardNo);
        if (inCard ==null||outCard==null){
            throw new BizException("请检查转入转出账户");
        }

        if(Double.parseDouble(outCard.getBalance())<v){
            throw new BizException("账户余额不足");
        }


        //  2018/7/20 转出

        String sub = MoneyUtils.sub(outCard.getBalance(), money);
        int i = cardMapper.upBalance(outCardNo, sub, outCard.getVersion());
        if(i!=1){
            throw new BizException("转账失败");
        }

        //插入流水
        Flow flow = new Flow();
        flow.setCardId(outCard.getId());
        flow.setAmount(money);
        flow.setCardNum(outCard.getCardNum());
        flow.setCreateTime(new Date());
        flow.setFlowDesc("转账支出");//流水类型 1-存钱 2-取钱 3-转账支出 4-转账收入
        flow.setFlowType((byte)3);
        flow.setUserId(userId);
        int i1 = flowMapper.insertSelective(flow);
        if(i1!=1){
            throw new BizException("转账失败");
        }

        //  2018/7/20 转入

        String sum = MoneyUtils.sum(inCard.getBalance(), money);
        int s = cardMapper.upBalance(inCardNo,sum, inCard.getVersion());
        if(s!=1){
            throw new BizException("转账失败");
        }

        //插入流水
        flow.setCardId(inCard.getId());
        flow.setAmount(money);
        flow.setCardNum(inCard.getCardNum());
        flow.setCreateTime(new Date());
        flow.setFlowDesc("转账收入");//流水类型 1-存钱 2-取钱 3-转账支出 4-转账收入
        flow.setFlowType((byte)4);
        flow.setUserId(inCard.getUserId());
        int s2 = flowMapper.insertSelective(flow);
        if(s2!=1){
            throw new BizException("转账失败");
        }


    }


    //查卡号
    public List<String> findCardNoByUserid(Integer userId){
        if (userId==null){
            throw new BizException("请先登录");
        }
        List<String> cardNos = cardMapper.findCardNoByUserid(userId);
        return cardNos;
    }


    //查询流水
    public  PageUtils  qflow(String cardNo,String pwd,Integer currentPageindex){
        //  2018/7/19 校验参数
        if (StringUtils.isBlank(cardNo)||StringUtils.isBlank(pwd)){
            throw new BizException("请填写必填参数");
        }
        if(currentPageindex<=0){
            throw new BizException("页数不能小于0");
        }

        //  2018/7/19 校验账户密码
        Card card = cardMapper.seByC(cardNo);
        if(card == null||!card.getPwd().equals(pwd)){
            throw new BizException("账户或密码不正确");
        }

        //查流水
        Integer tol = flowMapper.seTolflowByC(cardNo);
        PageUtils pageUtils = new PageUtils(tol, currentPageindex);
        List<Flow> flows = flowMapper.seflowByC(cardNo,pageUtils.getStartIndex(),pageUtils.getPageRows());


        ArrayList<FlowDto> flows1 = new ArrayList<>();
        for (Flow flow:flows){
            FlowDto flowDto = new FlowDto();
            flows1.add(flowDto);
            flowDto.setAmount(flow.getAmount());
            flowDto.setCardNum(flow.getCardNum());
            flowDto.setCreateTime(DateUtil.datefoemat(flow.getCreateTime()));
            flowDto.setDesc(flow.getFlowDesc());
        }

        pageUtils.setData(flows1);
        return  pageUtils;
    }


    //查最近10笔流水
    public  ArrayList<FlowDto> qTenflow(Integer userId){
        if(userId == null){
            throw new BizException("请先登录");
        }

        List<Flow> flows = flowMapper.seTenflowByUserId(userId);

        ArrayList<FlowDto> flows1 = new ArrayList<>();
        for (Flow flow:flows){
            FlowDto flowDto = new FlowDto();
            flows1.add(flowDto);
            flowDto.setAmount(flow.getAmount());
            flowDto.setCardNum(flow.getCardNum());
            flowDto.setCreateTime(DateUtil.datefoemat(flow.getCreateTime()));
            flowDto.setDesc(flow.getFlowDesc());
        }

        return flows1;
    }

    //查银行卡信息
    public ArrayList<CardDto> cardInf(Integer userId){
        List<Card> cards = cardMapper.findCardByUserid(userId);

        ArrayList<CardDto> cardDtos = new ArrayList<>();
        for (Card card:cards){
            CardDto cardDto = new CardDto();
            cardDtos.add(cardDto);
            cardDto.setBalance(card.getBalance());
            cardDto.setCardNum(card.getCardNum());
            cardDto.setCreateTime(DateUtil.datefoemat(card.getCreateTime()));
            cardDto.setId(card.getId());
            cardDto.setModifyTime(DateUtil.datefoemat(card.getModifyTime()));
            cardDto.setPwd(card.getPwd());
            cardDto.setStatus(card.getStatus());
            cardDto.setUserId(card.getUserId());
            cardDto.setVersion(card.getVersion());
        }

        return cardDtos;

    }


    //转账(延迟到账)
    @Transactional(rollbackFor = Exception.class)
    public void transferlate(String inCardNo,String outCardNo,String pwd,String money,Integer userId){

        //参数检验
        if(StringUtils.isBlank(inCardNo)||StringUtils.isBlank(outCardNo)
                ||StringUtils.isBlank(pwd) ||StringUtils.isBlank(money)){

            throw new BizException("请填写必填参数");
        }

        double v = Double.parseDouble(money);
        if (v<=0){
            throw new BizException("转账金额不能小于0");
        }

        if (userId == null){
            throw new BizException("请先登陆");
        }


        //效验账户密码
        Card card = cardMapper.seByC(outCardNo);
        if (card == null||!card.getPwd().equals(pwd)){
            throw new BizException("账号或密码错误");
        }



        Card inCard = cardMapper.seByC(inCardNo);
        Card outCard = cardMapper.seByC(outCardNo);
        if (inCard ==null||outCard==null){
            throw new BizException("请检查转入转出账户");
        }

        if(Double.parseDouble(outCard.getBalance())<v){
            throw new BizException("账户余额不足");
        }


        //  2018/7/20 转出

        String sub = MoneyUtils.sub(outCard.getBalance(), money);
        int i = cardMapper.upBalance(outCardNo, sub, outCard.getVersion());
        if(i!=1){
            throw new BizException("转账失败");
        }

        //插入流水
        Flow flow = new Flow();
        flow.setCardId(outCard.getId());
        flow.setAmount(money);
        flow.setCardNum(outCard.getCardNum());
        flow.setCreateTime(new Date());
        flow.setFlowDesc("转账支出");//流水类型 1-存钱 2-取钱 3-转账支出 4-转账收入
        flow.setFlowType((byte)3);
        flow.setUserId(userId);
        int i1 = flowMapper.insertSelective(flow);
        if(i1!=1){
            throw new BizException("转账失败");
        }

        //  插入转账表

        Transfer transfer = new Transfer();
        transfer.setOutCardNo(outCardNo);
        transfer.setInCardNo(inCardNo);
        transfer.setMoney(money);
        transfer.setStatus("0");
        transfer.setCreateTime(new Date());

        int insert = transferMapper.insert(transfer);
        if(insert!=1){
            throw new BizException("转账失败");
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public void transferin(String inCardNo,String oldStatus,String money,Integer id){

        Card inCard = cardMapper.seByC(inCardNo);
        String sum = MoneyUtils.sum(inCard.getBalance(), money);
        int s = cardMapper.upBalance(inCardNo,sum, inCard.getVersion());
        if(s!=1){
            throw new BizException("转账失败");
        }

        //插入流水
        Flow flow = new Flow();
        flow.setCardId(inCard.getId());
        flow.setAmount(money);
        flow.setCardNum(inCard.getCardNum());
        flow.setCreateTime(new Date());
        flow.setFlowDesc("转账收入");//流水类型 1-存钱 2-取钱 3-转账支出 4-转账收入
        flow.setFlowType((byte)4);
        flow.setUserId(inCard.getUserId());
        int s2 = flowMapper.insertSelective(flow);
        if(s2!=1){
            throw new BizException("转账失败");
        }

        int i = transferMapper.updateStatus(id, oldStatus,"1", new Date());
        //int a = 10/0;
        if(i!=1){
            throw new BizException("转账失败");
        }

    }

    //转账取消
    @Transactional(rollbackFor = Exception.class)
    public void transferCancel(Transfer transfer){
        //  2018/7/28 转出账户加钱 添加流水 转账状态改为取消
        Card card = cardMapper.seByCUpData(transfer.getOutCardNo());
        String sum = MoneyUtils.sum(card.getBalance(), transfer.getMoney());
        int i = cardMapper.upBalanceNoLock(transfer.getOutCardNo(), transfer.getMoney());
        if(i!=1){
           throw new BizException("系统异常,请联系客服");
        }

        Flow flow = new Flow();
        flow.setCardId(card.getId());
        flow.setAmount(transfer.getMoney());
        flow.setCardNum(card.getCardNum());
        flow.setCreateTime(new Date());
        flow.setFlowDesc("转账退款");//流水类型 1-存钱 2-取钱 3-转账支出 4-转账收入 5-转账退款
        flow.setFlowType((byte)5);
        flow.setUserId(card.getUserId());
        int s2 = flowMapper.insertSelective(flow);
        if(s2!=1){
            throw new BizException("添加流水失败,请联系客服");
        }

        int i1 = transferMapper.updateStatus(transfer.getId(), transfer.getStatus(), "3", new Date());
        if(i1!=1){
            throw new BizException("账单处理失败,请联系客服");
        }


    }

}

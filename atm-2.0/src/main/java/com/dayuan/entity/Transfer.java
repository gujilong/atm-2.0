package com.dayuan.entity;

import java.util.Date;

public class Transfer {
    private Integer id;

    private String outCardNo;

    private String inCardNo;

    private String money;

    private String status;

    private Date createTime;

    private Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutCardNo() {
        return outCardNo;
    }

    public void setOutCardNo(String outCardNo) {
        this.outCardNo = outCardNo == null ? null : outCardNo.trim();
    }

    public String getInCardNo() {
        return inCardNo;
    }

    public void setInCardNo(String inCardNo) {
        this.inCardNo = inCardNo == null ? null : inCardNo.trim();
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
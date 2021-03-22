package com.cqut.why.authoringsystem.authoringsystem.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class EquipmentDetailInfoDTO {
    // 设备
    private String eName; // 设备名称
    private String model; // 设备型号
    private String sn; // 设备序列码
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date insertedAt; // 入库时间
    private String createUserName;// 登记人员
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date grantAt; // 授权时间
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date activateAt; // 激活时间
    private String receiveUserName;

    // 项目
    private String no; // 项目编号
    private String pName; // 项目名称
    // 客户
    private String cName;
    private String contactName;
    private String contactTel;

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Date insertedAt) {
        this.insertedAt = insertedAt;
    }

    public Date getGrantAt() {
        return grantAt;
    }

    public void setGrantAt(Date grantAt) {
        this.grantAt = grantAt;
    }

    public Date getActivateAt() {
        return activateAt;
    }

    public void setActivateAt(Date activateAt) {
        this.activateAt = activateAt;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }


}

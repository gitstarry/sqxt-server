package com.cqut.why.authoringsystem.authoringsystem.entity.dto;

import javax.xml.crypto.Data;

public class EquipmentInfoDTO {
    private Integer id;
    private String name;
    private String model;
    private String sn;
    private Data grantAt;
    private Data activateAt;
    private String receiveUserName;
    private String contractNo;
    private String pName; // 算法名称
    private String category; // 类型（0算法，1软件）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Data getGrantAt() {
        return grantAt;
    }

    public void setGrantAt(Data grantAt) {
        this.grantAt = grantAt;
    }

    public Data getActivateAt() {
        return activateAt;
    }

    public void setActivateAt(Data activateAt) {
        this.activateAt = activateAt;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

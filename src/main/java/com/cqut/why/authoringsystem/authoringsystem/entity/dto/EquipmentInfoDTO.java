package com.cqut.why.authoringsystem.authoringsystem.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class EquipmentInfoDTO {
    private Integer id;
    private String equipmentName;
    private String equipmentModel;
    private String equipmentSn;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date grantAt;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date activateAt;
    private String receiveUserName;
    private String contractNo;
    private String programName; // 算法名称

    public EquipmentInfoDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(String equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public String getEquipmentSn() {
        return equipmentSn;
    }

    public void setEquipmentSn(String equipmentSn) {
        this.equipmentSn = equipmentSn;
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

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}

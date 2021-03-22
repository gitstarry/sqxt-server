package com.cqut.why.authoringsystem.authoringsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class LicenseMigrationDetail {
    private Integer id;
    private String programName;
    private Integer projectId;
    private String projectName;
    private Integer equipmentId;
    private String equipmentName;
    private String contractNo;
    private String receiveUserName;
    private String customerName;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date grantAt;
    private String equipmentModel;
    private String equipmentSn;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date activateAt;


    public LicenseMigrationDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getGrantAt() {
        return grantAt;
    }

    public void setGrantAt(Date grantAt) {
        this.grantAt = grantAt;
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

    public Date getActivateAt() {
        return activateAt;
    }

    public void setActivateAt(Date activateAt) {
        this.activateAt = activateAt;
    }
}

package com.cqut.why.authoringsystem.authoringsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EquipmentProgramVersion {
    private Integer id;
    private Integer equipmentId;
    private Integer upgradeUserId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date upgradeAt;
    private Integer programVersionId;
    private String remark;
    private String equipmentName;
    private String equipmentSn;
    private Integer projectId;
    private String projectName;
    private Integer programId;
    private String programName;
    private String programVersion;
    private Integer programCategory;
    private Integer programVersionOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getProgramVersionId() {
        return programVersionId;
    }

    public void setProgramVersionId(Integer programVersionId) {
        this.programVersionId = programVersionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentSn() {
        return equipmentSn;
    }

    public void setEquipmentSn(String equipmentSn) {
        this.equipmentSn = equipmentSn;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramVersion() {
        return programVersion;
    }

    public void setProgramVersion(String programVersion) {
        this.programVersion = programVersion;
    }

    public Integer getProgramCategory() {
        return programCategory;
    }

    public void setProgramCategory(Integer programCategory) {
        this.programCategory = programCategory;
    }

    public Integer getUpgradeUserId() {
        return upgradeUserId;
    }

    public void setUpgradeUserId(Integer upgradeUserId) {
        this.upgradeUserId = upgradeUserId;
    }

    public Date getUpgradeAt() {
        return upgradeAt;
    }

    public void setUpgradeAt(Date upgradeAt) {
        this.upgradeAt = upgradeAt;
    }

    public Integer getProgramVersionOrder() {
        return programVersionOrder;
    }

    public void setProgramVersionOrder(Integer programVersionOrder) {
        this.programVersionOrder = programVersionOrder;
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
}

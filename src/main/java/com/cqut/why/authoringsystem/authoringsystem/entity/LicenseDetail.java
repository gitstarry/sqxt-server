package com.cqut.why.authoringsystem.authoringsystem.entity;

import java.util.Date;

public class LicenseDetail {
    private Integer id;

    private Integer licenseId;

    private Integer programId;

    private Date grantAt;

    private Date expiredAt;

    private String equipmentName;

    private String projectName;

    private String programName;

    private Integer programCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Integer licenseId) {
        this.licenseId = licenseId;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Date getGrantAt() {
        return grantAt;
    }

    public void setGrantAt(Date grantAt) {
        this.grantAt = grantAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public Integer getProgramCategory() {
        return programCategory;
    }

    public void setProgramCategory(Integer programCategory) {
        this.programCategory = programCategory;
    }
}

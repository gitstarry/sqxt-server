package com.cqut.why.authoringsystem.authoringsystem.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProgramVersionDTO {
    private Integer id;

    private String programName;

    private String maintainerContact;

    private String version;

    private Integer status;

    private String remark;

    private String descripe;

    private String maintainer;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date insertedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDescripe() {
        return descripe;
    }

    public void setDescripe(String descripe) {
        this.descripe = descripe;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public Date getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Date insertedAt) {
        this.insertedAt = insertedAt;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getMaintainerContact() {
        return maintainerContact;
    }

    public void setMaintainerContact(String maintainerContact) {
        this.maintainerContact = maintainerContact;
    }
}

package com.cqut.why.authoringsystem.authoringsystem.entity.dto;

public class EquipmentUpgradeStatusDTO {
    private Integer id;
    private Integer status;
    private String programName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}

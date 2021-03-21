package com.cqut.why.authoringsystem.authoringsystem.entity.dto;

import javax.xml.crypto.Data;

public class EquipmentDetailDTO {
    private Integer id;
    private String name;
    private String sn;
    private String createUserId;
    private Data insertedAt;

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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Data getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Data insertedAt) {
        this.insertedAt = insertedAt;
    }
}

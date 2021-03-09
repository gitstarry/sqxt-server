package com.cqut.why.authoringsystem.authoringsystem.entity.dto;

import com.sun.istack.NotNull;

public class SysUserInfoDTO {
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String  username;
    private String[] tag;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getTag() {
        return tag;
    }

    public void setTag(String[] tag) {
        this.tag = tag;
    }
}

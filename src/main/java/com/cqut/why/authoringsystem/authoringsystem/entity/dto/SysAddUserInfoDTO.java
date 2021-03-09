package com.cqut.why.authoringsystem.authoringsystem.entity.dto;

import com.sun.istack.NotNull;

public class SysAddUserInfoDTO {
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String  username;

    private String[] tag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

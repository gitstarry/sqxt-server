package com.cqut.why.authoringsystem.authoringsystem.entity.dto;


import com.sun.istack.NotNull;

public class UserLoginDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String captcha;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}

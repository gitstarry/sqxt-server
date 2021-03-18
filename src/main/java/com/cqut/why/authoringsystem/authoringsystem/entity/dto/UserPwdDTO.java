package com.cqut.why.authoringsystem.authoringsystem.entity.dto;

import com.sun.istack.NotNull;

public class UserPwdDTO {
    @NotNull
    private Integer id;
    @NotNull
    private String originPassword;
    @NotNull
    private String newPassword;
    @NotNull
    private String confirmPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginPassword() {
        return originPassword;
    }

    public void setOriginPassword(String originPassword) {
        this.originPassword = originPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

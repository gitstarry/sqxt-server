package com.cqut.why.authoringsystem.authoringsystem.entity.dto;


import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class ChangePasswordDTO {

    private Integer id;

    private String oldPassword;

    private String newPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

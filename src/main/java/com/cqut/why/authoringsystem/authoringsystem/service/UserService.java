package com.cqut.why.authoringsystem.authoringsystem.service;


import com.cqut.why.authoringsystem.authoringsystem.entity.User;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ChangePasswordDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.UserInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.UserLoginDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.SysUserQueryParams;

import java.util.List;

public interface UserService {

    User getUserByUsername(String username);

    void changePassword(ChangePasswordDTO changePasswordDTO);

    UserInfoDTO login(UserLoginDTO loginDTO);

    Integer getCount(SysUserQueryParams sysUserQueryParams);

    List<User> getSysUsers(SysUserQueryParams sysUserQueryParams);

}

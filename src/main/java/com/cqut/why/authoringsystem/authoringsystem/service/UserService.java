package com.cqut.why.authoringsystem.authoringsystem.service;


import com.cqut.why.authoringsystem.authoringsystem.entity.User;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.*;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.SysUserQueryParams;

import java.util.List;

public interface UserService {

    User getUserByUsername(String username);

    void changePassword(ChangePasswordDTO changePasswordDTO);

    UserInfoDTO login(UserLoginDTO loginDTO);

    Integer getCount(SysUserQueryParams sysUserQueryParams);

    List<User> getSysUsers(SysUserQueryParams sysUserQueryParams);

    User getById(Integer id);

    boolean addOneUser(SysAddUserInfoDTO sysUserInfoDTO);

    boolean modifyUser(SysUserInfoDTO sysUserInfoDTO);

    int changeStatus(Integer id, Integer status);

    boolean deleteUser(Integer id);

    boolean updatePassword(UserPwdDTO pwdDTO);
}

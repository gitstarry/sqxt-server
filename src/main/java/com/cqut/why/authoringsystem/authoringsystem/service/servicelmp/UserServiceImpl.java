package com.cqut.why.authoringsystem.authoringsystem.service.servicelmp;


import com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler.BusinessException;
import com.cqut.why.authoringsystem.authoringsystem.config.util.JwtUtil;
import com.cqut.why.authoringsystem.authoringsystem.config.util.MD5Util;
import com.cqut.why.authoringsystem.authoringsystem.dao.UserMapper;
import com.cqut.why.authoringsystem.authoringsystem.entity.Role;
import com.cqut.why.authoringsystem.authoringsystem.entity.User;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ChangePasswordDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.UserInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.UserLoginDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.SysUserQueryParams;
import com.cqut.why.authoringsystem.authoringsystem.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        changePasswordDTO.setNewPassword(MD5Util.encode(changePasswordDTO.getNewPassword()));
        userMapper.changePassword(changePasswordDTO);
    }

    @Override
    public UserInfoDTO login(UserLoginDTO loginDTO) {
        User user = userMapper.loadByUserName(loginDTO.getUsername());
        Role role = userMapper.getRole(user.getId());
        //用户不存在则抛出异常
        if (user == null) {
            throw new BusinessException("用户尚未注册");
        }
        System.out.println(user.getPassword());
        System.out.println(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()));
        //如果密码错误则抛出异常
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BusinessException("密码错误");
        }
        User authUser = new User();
        BeanUtils.copyProperties(user, authUser);
        String token = jwtUtil.generateJsonWebToken(authUser);
        UserInfoDTO userInfo = new UserInfoDTO();
        BeanUtils.copyProperties(user, userInfo);
        userInfo.setToken(token);
        userInfo.setRoles((role.getTag() + "").split(","));
        return userInfo;
    }

    @Override
    public Integer getCount(SysUserQueryParams sysUserQueryParams) {
        return userMapper.selectCount(sysUserQueryParams);
    }

    @Override
    public List<User> getSysUsers(SysUserQueryParams sysUserQueryParams) {
        return userMapper.selectPage(sysUserQueryParams);
    }
}

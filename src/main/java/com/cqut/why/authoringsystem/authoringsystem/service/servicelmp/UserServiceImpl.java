package com.cqut.why.authoringsystem.authoringsystem.service.servicelmp;


import com.cqut.why.authoringsystem.authoringsystem.config.util.BeanMapper.BeanMapper;
import com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler.BusinessException;
import com.cqut.why.authoringsystem.authoringsystem.config.util.JwtUtil;
import com.cqut.why.authoringsystem.authoringsystem.config.util.MD5Util;
import com.cqut.why.authoringsystem.authoringsystem.dao.UserMapper;
import com.cqut.why.authoringsystem.authoringsystem.entity.Role;
import com.cqut.why.authoringsystem.authoringsystem.entity.User;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.*;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.SysUserQueryParams;
import com.cqut.why.authoringsystem.authoringsystem.service.UserService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
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
        userInfo.setRoles((user.getTag() + "").split(","));
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

    @Override
    public User getById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public boolean addOneUser(SysAddUserInfoDTO sysUserInfoDTO) {
        User record = userMapper.loadByUserName(sysUserInfoDTO.getUsername());
        if (record != null) {
            throw new BusinessException("用户名已注册");
        }
        User user = BeanMapper.map(sysUserInfoDTO, User.class);
        user.setPassword(passwordEncoder.encode(sysUserInfoDTO.getPassword()));
        user.setTag(StringUtils.join(Arrays.asList(sysUserInfoDTO.getTag()), ','));
        //设置创建时间和修改时间
        user.setCreatedAt(new Date());
        return userMapper.insert(user) == 1;
    }

    @Override
    public boolean modifyUser(SysUserInfoDTO sysUserInfoDTO) {
        User user = userMapper.getUserById(sysUserInfoDTO.getId());
        if (user == null) {
            throw new BusinessException("该用户不存在");
        }
        BeanUtils.copyProperties(sysUserInfoDTO, user);
        user.setTag(StringUtils.join(Arrays.asList(sysUserInfoDTO.getTag()), ','));
        //设置创建时间和修改时间
        user.setCreatedAt(new Date());
        return userMapper.updateByPrimaryKey(user) == 1;
    }

    @Override
    public int changeStatus(Integer id, Integer status) {
        User user = userMapper.getUserById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return userMapper.changeStatus(id, status);
    }

    @Override
    public boolean deleteUser(Integer id) {
        User user = userMapper.getUserById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return userMapper.deleteUser(id);
    }

    @Override
    public boolean updatePassword(UserPwdDTO pwdDTO) {
        User user = userMapper.getUserById(pwdDTO.getId());
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        if (!passwordEncoder.matches(pwdDTO.getOriginPassword(), user.getPassword())) {
            throw new BusinessException("原密码输入错误");
        }
        if (!pwdDTO.getNewPassword().equals(pwdDTO.getConfirmPassword())) {
            throw new BusinessException("新密码输入不一致");
        }
        user.setPassword(passwordEncoder.encode(pwdDTO.getNewPassword()));
        return userMapper.updateByPrimaryKey(user) == 1;
    }

}

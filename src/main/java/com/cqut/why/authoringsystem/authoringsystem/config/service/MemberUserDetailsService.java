package com.cqut.why.authoringsystem.authoringsystem.config.service;

import com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler.BusinessException;
import com.cqut.why.authoringsystem.authoringsystem.dao.UserMapper;
import com.cqut.why.authoringsystem.authoringsystem.entity.User;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.PermissionEntity;
import com.cqut.why.authoringsystem.authoringsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MemberUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.根据该用户名称查询在数据库中是否存在
        User user = userMapper.findByUsername(username);
        if (user == null) {
//            throw new BusinessException();
            return null;
        }
        // 2.查询对应的用户权限
        List<PermissionEntity> listPermission = userMapper.findPermissionByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        listPermission.forEach(users -> {
            authorities.add(new SimpleGrantedAuthority(users.getTag()));
        });
        /*log.info(">>>authorities:{}<<<*******************", authorities);*/
        // 3.将该权限添加到security
        user.setAuthorities(authorities);
        return user;
    }

}

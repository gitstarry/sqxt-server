package com.cqut.why.authoringsystem.authoringsystem.controller;


import com.cqut.why.authoringsystem.authoringsystem.config.filter.JWTAuthorizationFilter;
import com.cqut.why.authoringsystem.authoringsystem.entity.User;
import com.cqut.why.authoringsystem.authoringsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseController {

    @Autowired
    UserService userService;

    public User currentUser(){

        String username = JWTAuthorizationFilter.currentUsername.get();

//        return userService.getUserByUsername(username);
        return userService.getUserByUsername("why1");

    }
}

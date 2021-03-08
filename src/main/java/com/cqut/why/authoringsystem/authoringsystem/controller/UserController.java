package com.cqut.why.authoringsystem.authoringsystem.controller;


import com.cqut.why.authoringsystem.authoringsystem.config.util.BeanMapper.BeanMapper;
import com.cqut.why.authoringsystem.authoringsystem.config.util.CaptchaHelper;
import com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler.BusinessException;
import com.cqut.why.authoringsystem.authoringsystem.config.util.MD5Util;
import com.cqut.why.authoringsystem.authoringsystem.config.util.jsonResult.JSONResult;
import com.cqut.why.authoringsystem.authoringsystem.config.util.message.Message;
import com.cqut.why.authoringsystem.authoringsystem.entity.User;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.ChangePasswordDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.SysUserListDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.UserInfoDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.UserLoginDTO;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.SysUserQueryParams;
import com.cqut.why.authoringsystem.authoringsystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin
@Api(tags = "用户")
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    UserService userService;
    @Autowired
    private CaptchaHelper captchaHelper;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public JSONResult<UserInfoDTO> login(HttpServletRequest request, @RequestBody UserLoginDTO loginDTO) {
        //对图片验证码校验
        try {
            captchaHelper.validateThrow(request, loginDTO.getCaptcha(), request.getSession().getId());
        } catch (CaptchaHelper.CaptchaException e) {
            throw new BusinessException(e.getMessage());
        }
        UserInfoDTO userInfo = userService.login(loginDTO);
        JSONResult<UserInfoDTO> jsonResult = new JSONResult<>();
        jsonResult.setData(userInfo).setMessage("登录成功");
        return jsonResult;
    }

    @ApiOperation(value = "发送验证码")
    @GetMapping("/getCaptcha")
    public void getRandomCaptcha(HttpServletRequest request, HttpServletResponse response) {
        CaptchaHelper.getCaptchaHelper().generate(request, response, request.getSession().getId());
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/changePassword")
    public JSONResult changePassword(ChangePasswordDTO changePasswordDTO) {
        JSONResult jsonResult = new JSONResult();
        changePasswordDTO.setId(currentUser().getId());
        if (changePasswordDTO.getOldPassword().equals(changePasswordDTO.getNewPassword())) {
            throw new BusinessException("两次密码一致");
        }
        if (!(MD5Util.encode(changePasswordDTO.getOldPassword()).equals(currentUser().getPassword()))) {
            throw new BusinessException("旧密码输入错误");
        } else {
            String newPassword = changePasswordDTO.getNewPassword();
            userService.changePassword(changePasswordDTO);
            jsonResult.setMessage(new Message("DB.UPDATE_SUCCESS", "密码"));
            return jsonResult;
        }
    }
    @ApiOperation(value = "分页模糊查询用户信息")
    @PostMapping("/getSysUsers")
    public JSONResult<List<SysUserListDTO>> getSysUsers(@RequestBody SysUserQueryParams sysUserQueryParams) {
        // 用户总数
        Integer count = userService.getCount(sysUserQueryParams);
        List<User> sysUsers = userService.getSysUsers(sysUserQueryParams);
        System.out.println(sysUsers.get(0).getCreatedAt());
        List<SysUserListDTO> userListDTOS = BeanMapper.mapList(sysUsers, SysUserListDTO.class);
        userListDTOS.forEach(u -> {
            //如果角色为空则角色列表为空数组
            if (u.getTag() == null) {
                u.setTagArr(new String[]{});
            } else {
                u.setTagArr(u.getTag().split(","));
            }
        });
        JSONResult<List<SysUserListDTO>> jsonResult = new JSONResult<>();
        jsonResult.setData(userListDTOS);
        jsonResult.setTotalCount(count);
        return jsonResult;
    }




}

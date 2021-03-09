package com.cqut.why.authoringsystem.authoringsystem.controller;


import com.cqut.why.authoringsystem.authoringsystem.config.util.BeanMapper.BeanMapper;
import com.cqut.why.authoringsystem.authoringsystem.config.util.CaptchaHelper;
import com.cqut.why.authoringsystem.authoringsystem.config.util.GlobalExceptionHandler.BusinessException;
import com.cqut.why.authoringsystem.authoringsystem.config.util.MD5Util;
import com.cqut.why.authoringsystem.authoringsystem.config.util.jsonResult.JSONResult;
import com.cqut.why.authoringsystem.authoringsystem.config.util.message.Message;
import com.cqut.why.authoringsystem.authoringsystem.entity.User;
import com.cqut.why.authoringsystem.authoringsystem.entity.dto.*;
import com.cqut.why.authoringsystem.authoringsystem.entity.params.SysUserQueryParams;
import com.cqut.why.authoringsystem.authoringsystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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

    @ApiOperation(value = "根据id获取用户信息")
    @GetMapping("/getUserInfo")
    public JSONResult<SysUserInfoDTO> getUserInfo(Integer id) {
        JSONResult<SysUserInfoDTO> jsonResult = new JSONResult<>();
        User user = userService.getById(id);
        SysUserInfoDTO userInfoDTO = new SysUserInfoDTO();
        BeanUtils.copyProperties(user, userInfoDTO);
        if (user.getTag() == null) {  // 如果角色为空
            userInfoDTO.setTag(new String[]{}); // 角儿置为空数组
        } else {
            userInfoDTO.setTag(user.getTag().split(","));
        }
        jsonResult.setData(userInfoDTO);
        return jsonResult;
    }

    @ApiOperation(value = "新增用户信息")
    @PostMapping("/addUser")
    public JSONResult addSysUser(@RequestBody SysAddUserInfoDTO sysUserInfoDTO) {
        JSONResult jsonResult = new JSONResult();
        boolean flag = userService.addOneUser(sysUserInfoDTO);
        if (flag) {
            jsonResult.setMessage(new Message("DB.ADD_SUCCESS", "用户"));
            return jsonResult;
        } else {
            throw new BusinessException(new Message("DB.ADD_FAILED", "用户"));
        }
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/modifyUser")
    public JSONResult modifyUser(@RequestBody SysUserInfoDTO sysUserInfoDTO) {
        JSONResult jsonResult = new JSONResult();
        boolean updated = userService.modifyUser(sysUserInfoDTO);
        if (updated) {
            jsonResult.setMessage(new Message("DB.UPDATE_SUCCESS", "用户信息"));
            return jsonResult;
        } else {
            throw new BusinessException(new Message("DB.UPDATE_FAILED", "用户信息"));
        }
    }


    @ApiOperation("禁用或开启")
    @GetMapping("/changeStatus")
    public JSONResult forbidUser(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
        JSONResult jsonResult = new JSONResult();
        int res = userService.changeStatus(id, status);
        if (res > 0) {
            if (status == 0)
                jsonResult.setMessage("禁用成功");
            else if (status == 1) {
                jsonResult.setMessage("开启成功");
            } else {
                throw new BusinessException("参数错误");
            }
        } else {
            throw new BusinessException("状态修改失败");
        }
        return jsonResult;
    }

    @ApiOperation("删除用户信息")
    @GetMapping("/deleteUser")
    public JSONResult deleteUser(@RequestParam("id") Integer id) {
        JSONResult jsonResult = new JSONResult();
        boolean delete = userService.deleteUser(id);
        if (delete) {
            jsonResult.setMessage(new Message("DB.DELETE_SUCCESS", "用户信息"));
            return jsonResult;
        } else {
            throw new BusinessException(new Message("DB.DELETE_FAILED", "用户信息"));
        }
    }

}

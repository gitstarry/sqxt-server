package com.cqut.why.authoringsystem.authoringsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@CrossOrigin
public class IndexController extends BaseController {

    @ResponseBody
    @RequestMapping("/addMember")
    public String addMember() {

        return "新增用户";
    }

    @ResponseBody
    @RequestMapping("/delMember")
    public String delMember() {
        return "删除用户";
    }

    @ResponseBody
    @RequestMapping("/updateMember")
    public String updateMember() {
//        throw new BusinessException(new Message("DB.UPDATE_SUCCESS","User"));
        return "修改用户";
    }

    @ResponseBody
    @RequestMapping("/showMember")
    public String showMember() {
        return "查询用户";
    }

    @ResponseBody
    @RequestMapping("/eatMember")
    public String eatMember() {
        return "吃掉用户";
    }


    @ResponseBody
    @RequestMapping("/loveMember")
    public String loveMember() {
        return "爱了用户";
    }


    @ResponseBody
    @RequestMapping("/updateMembers")
    public String updateMembers() {

        return "/************/*/*/*";
    }


    @ResponseBody
    @RequestMapping("/updateMember/sss")
    public String sss() {
        return "@@@@@@@";
    }

    @ResponseBody
    @RequestMapping("/updateMemberss/sss")
    public String updateMemberss() {
        return "$$$$$$$";
    }

}

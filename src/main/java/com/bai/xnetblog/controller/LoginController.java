package com.bai.xnetblog.controller;

import com.bai.xnetblog.pojo.Category;
import com.bai.xnetblog.pojo.RespBean;
import com.bai.xnetblog.pojo.User;
import com.bai.xnetblog.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserServer userServer;

    @RequestMapping(value = "/login_error")
    public RespBean loginError() {
        return new RespBean("error", "登陆失败");
    }

    @RequestMapping("/login_success")
    public RespBean loginSuccess() {
        return new RespBean("success", "登陆成功");
    }

    @RequestMapping("/login_page")
    public RespBean loginPage() {
        return new RespBean("error", "尚未登陆，请登录");
    }

    @RequestMapping("reg")
    public RespBean reg(User user) {
        int reg = userServer.reg(user);
        if (reg == 1) {
            return new RespBean("success", "注册成功");
        }
        if (reg == 2) {
            return new RespBean("error", "注册失败，用户名重复");
        }
        if (reg == 0) {
            return new RespBean("error", "注册失败");
        }
        return new RespBean("error", "你个大傻逼");
    }
}

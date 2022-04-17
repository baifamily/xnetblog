package com.bai.xnetblog.controller;

import com.bai.xnetblog.Utils.CurrentUser;
import com.bai.xnetblog.pojo.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class UserController {

    @RequestMapping("/currentUserName")
    public String getCurrentUserName() {
        return CurrentUser.getCurrentUser().getNickname();
    }

    @RequestMapping("/currentUserId")
    public Long getCurrentUserId() {
        return CurrentUser.getCurrentUser().getId();
    }

    @RequestMapping("/currentUserEmail")
    public String getCurrentEmail() {
        return CurrentUser.getCurrentUser().getEmail();
    }

    @RequestMapping("/isAdmin")
    public Boolean isAdmin() {
        Collection<GrantedAuthority> authorities = CurrentUser.getCurrentUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("超级管理员")) {
                return true;
            }
        }
        return false;
    }
}

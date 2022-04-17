package com.bai.xnetblog.Utils;

import com.bai.xnetblog.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser {
    public static User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}

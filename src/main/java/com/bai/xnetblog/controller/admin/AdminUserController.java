package com.bai.xnetblog.controller.admin;


import com.bai.xnetblog.Utils.CurrentUser;
import com.bai.xnetblog.pojo.RespBean;
import com.bai.xnetblog.pojo.Role;
import com.bai.xnetblog.pojo.User;

import com.bai.xnetblog.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    UserServer userServer;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> getUsersByNickName(@RequestParam("nickname") String nickname) {
        return userServer.getUsersByNickName(nickname);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id) {
        return userServer.getUserById(id);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public List<Role> getRole() {
        return userServer.getRole(CurrentUser.getCurrentUser().getId());
    }

    @RequestMapping(value = "/user/{uid}", method = RequestMethod.DELETE)
    public RespBean deleteUserByUid(@PathVariable Long uid) {
        if (userServer.deleteUserByUid(uid) == 1) {
            return new RespBean("success", "删除成功");
        } else {
            return new RespBean("error", "删除失败");
        }
    }

    @RequestMapping(value = "/user/enabled", method = RequestMethod.PUT)
    public RespBean updateEnabledByUid(@RequestParam("enabled") Boolean enabled, @RequestParam("uid") Long uid) {
        if (userServer.updateEnabledByUid(enabled, uid) == 1) {
            return new RespBean("success", "更新成功");
        } else {
            return new RespBean("error", "更新失败");
        }
    }

    @RequestMapping(value = "/user/role", method = RequestMethod.PUT)
    public RespBean updateRolesById(@RequestParam("rids") Long[] rids, @RequestParam("id") Long id) {
        if (userServer.updateRolesById(rids, id) == 1) {
            return new RespBean("success", "更新成功");
        } else {
            return new RespBean("error", "更新失败");
        }
    }
}

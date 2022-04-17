package com.bai.xnetblog.server;

import com.bai.xnetblog.Utils.CurrentUser;
import com.bai.xnetblog.config.MyPasswordEncoding;
import com.bai.xnetblog.dao.RoleMapper;
import com.bai.xnetblog.dao.UserMapper;
import com.bai.xnetblog.pojo.Role;
import com.bai.xnetblog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.Password;

import java.util.List;

@Service
@Transactional
public class UserServer implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MyPasswordEncoding myPasswordEncoding;

    @Autowired
    RoleMapper roleMapper;

    public List<User> getUsersByNickName(String nickName) {

        return userMapper.getUserByNickname(nickName);
    }

    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    public List<Role> getRole(Long id) {
        return roleMapper.getRoleByUid(id);
    }

    public int deleteUserByUid(Long uid) {
        return userMapper.deleteUserByUid(uid);
    }

    public int updateEnabledByUid(Boolean enabled, Long uid) {
        return userMapper.updateEnabledByUid(enabled, uid);
    }

    public int updateRolesById(Long[] rids, Long id) {
        return userMapper.updateRolesById(rids, id);
    }

    public int reg(User user) {
        if (userMapper.getUserByUsername(user.getUsername()) != null) {
            return 2;//重复用户名
        }
        user.setPassword(myPasswordEncoding.encode(user.getPassword()));
        user.setEnabled(true);
        Long result = userMapper.addUser(user);
        String[] roles = new String[]{"2"};
        int i = roleMapper.addRoles(CurrentUser.getCurrentUser().getId(), roles);
        if (i == roles.length && result == 1) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            //避免返回null，这里返回一个不含有任何值的User对象，在后期的密码比对过程中一样会验证失败
            return new User();
        }
        //查询用户的角色信息，并返回存入user中
        List<Role> roles = roleMapper.getRoleByUid(user.getId());
        user.setRoles(roles);
        return user;
    }
}

package com.bai.xnetblog.dao;

import com.bai.xnetblog.pojo.Role;
import com.bai.xnetblog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getUserByNickname(@Param("nickname") String nickname);

    User getUserById(@Param("id") Long id);

    int deleteUserByUid(@Param("uid") Long uid);

    int updateEnabledByUid(@Param("enabled") Boolean enabled,@Param("uid") Long uid);

    int updateRolesById(@Param("rids") Long[] rids,@Param("id") Long id);

    User getUserByUsername(@Param("username") String username);

    Long addUser(@Param("user") User user);

}

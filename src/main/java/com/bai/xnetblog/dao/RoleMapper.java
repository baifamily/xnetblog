package com.bai.xnetblog.dao;


import com.bai.xnetblog.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    int addRoles(@Param("roles") Long uid,@Param("uid") String[] roles);

    List<Role> getRoleByUid(@Param("uid") Long uid);

}
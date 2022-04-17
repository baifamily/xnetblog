package com.bai.xnetblog.dao;

import com.bai.xnetblog.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> getCategoryAll();

    int deleteCategoryByIds(@Param("ids") String[] ids);

    int insertCategory(Category category);

    int updateCategory(Category category);
}

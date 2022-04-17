package com.bai.xnetblog.server;

import com.bai.xnetblog.dao.CategoryMapper;
import com.bai.xnetblog.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class CategoryServer {

    @Autowired
    CategoryMapper categoryMapper;

    public List<Category> getCategoryAll() {
        return categoryMapper.getCategoryAll();
    }

    public int deleteCategoryByIds(String ids) {
        String[] id = ids.split(",");
        return categoryMapper.deleteCategoryByIds(id);
    }

    public int insertCategory(Category category) {
        category.setDate(new Timestamp(System.currentTimeMillis()));
        return categoryMapper.insertCategory(category);
    }

    public int updateCategory(Category category) {
        return categoryMapper.updateCategory(category);
    }
}

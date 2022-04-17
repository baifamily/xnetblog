package com.bai.xnetblog.controller;


import com.bai.xnetblog.pojo.Article;
import com.bai.xnetblog.pojo.Category;
import com.bai.xnetblog.pojo.RespBean;
import com.bai.xnetblog.server.CategoryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    CategoryServer categoryServer;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Category> getCategoryAll() {
        return categoryServer.getCategoryAll();
    }

    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean getCategoryAll(@PathVariable String ids) {
        if (categoryServer.deleteCategoryByIds(ids) == 1) {
            return new RespBean("success", "删除成功");
        } else {
            return new RespBean("error", "删除失败");
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean insertCategory(@RequestParam("category") Category category) {
        if (category.getCateName() != null && "".equals(category.getCateName())) {
            if (categoryServer.insertCategory(category) == 1) {
                return new RespBean("success", "添加成功");
            } else {
                return new RespBean("error", "添加失败");
            }
        } else {
            return new RespBean("error", "请输入栏目名称");
        }

    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public RespBean updateCategory(@RequestParam("category") Category category) {
        if (categoryServer.updateCategory(category) == 1) {
            return new RespBean("success", "更新成功");
        } else {
            return new RespBean("error", "更新失败");
        }

    }
}

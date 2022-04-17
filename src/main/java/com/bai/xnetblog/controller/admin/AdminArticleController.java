package com.bai.xnetblog.controller.admin;


import com.bai.xnetblog.pojo.RespBean;
import com.bai.xnetblog.server.ArticleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminArticleController {

    @Autowired
    ArticleServer articleServer;


    @RequestMapping(value = "/article/all", method = RequestMethod.GET)
    public Map<String, Object> getArticleAll(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count) {
        Map<String, Object> map = new HashMap<>();
        map.put("articles", articleServer.getArticleAll(page, count));
        map.put("totalCount", articleServer.getArticleTotal());
        return map;
    }

    @RequestMapping(value = "/article/dustbin", method = RequestMethod.PUT)
    public RespBean deleteArticle(Long[] aids, int state) {
        if (articleServer.deleteArticle(aids, state) == 1) {
            return new RespBean("success", "删除成功");
        }else {
            return new RespBean("error", "删除失败");
        }
    }
}


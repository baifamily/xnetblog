package com.bai.xnetblog.controller;

import com.bai.xnetblog.pojo.Article;
import com.bai.xnetblog.pojo.Comments;
import com.bai.xnetblog.pojo.RespBean;
import com.bai.xnetblog.server.CommentsServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/Comments")
public class CommentsController {

    @Autowired
    CommentsServlet commentsServlet;

//    @RequestParam(value = "state", defaultValue = "-1") Integer state
    @RequestMapping(value = "/aid", method = RequestMethod.POST)
    public Map<String,Object> getCommentsByUid(@RequestParam(value = "aid") Long aid) {
        List<Comments> comments = commentsServlet.getCommentsByAid(aid);
        int total = commentsServlet.getCommentsTotalByAid(aid);
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("comments", comments);
        objectObjectHashMap.put("total", total);
        return objectObjectHashMap;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public RespBean addComments(@RequestParam("comments") Comments comments) {
        if (commentsServlet.addComments(comments) != 1) {
            return new RespBean("error", "添加失败");
        } else {
            return new RespBean("success", "添加成功");
        }
    }
}

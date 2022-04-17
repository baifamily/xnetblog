package com.bai.xnetblog.server;

import com.bai.xnetblog.Utils.CurrentUser;
import com.bai.xnetblog.dao.CommentsMapper;
import com.bai.xnetblog.pojo.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Currency;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CommentsServlet {

    @Autowired
    CommentsMapper commentsMapper;

    public List<Comments> getCommentsByAid(Long aid) {
        return commentsMapper.getCommentsByAid(aid);
    }

    public int getCommentsTotalByAid(Long aid) {
        return commentsMapper.getCommentsTotalByAid(aid);
    }

    public int addComments(Comments comments) {
        Long uid = CurrentUser.getCurrentUser().getId();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        comments.setUid(uid);
        comments.setPublishDate(timestamp);
        return commentsMapper.addComments(comments);
    }
}
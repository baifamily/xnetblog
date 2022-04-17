package com.bai.xnetblog.dao;

import com.bai.xnetblog.pojo.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.List;

@Mapper
public interface CommentsMapper {

    List<Comments> getCommentsByAid(Long aid);

    int getCommentsTotalByAid(Long aid);

    int addComments(Comments comments);
}



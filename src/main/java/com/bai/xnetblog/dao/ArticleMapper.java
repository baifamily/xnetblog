package com.bai.xnetblog.dao;

import com.bai.xnetblog.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {

//  List<Article> getArticleAll(int page,int count);

    List<Article> getArticleAll(@Param("state") int state,@Param("start") int start,@Param("count") int count,@Param("uid") Long uid,@Param("keywords") String keywords);

    int getArticleTotal();

    int deleteArticle(@Param("aids") Long[] aids);

    int retreatState(@Param("aids") Long[] aids,@Param("state") int state);

    int updateArticle(Article article);

    int addNewArticle(Article article);

//  int addArticleTag(String[] dynamicTags, Long id);

    int getArticleAllTotal(@Param("state") int state,@Param("uid") Long uid,@Param("keywords") String keywords);

    Article getArticleById(Long id);

    int dustbinArticle(@Param("aids") Long[] aids,@Param("state") int state);

    int restoreArticle(Integer articleId);

    List<String> dataStatisticsTime(Long uid);

    List<Integer> dataStatisticsPV(Long uid);

}

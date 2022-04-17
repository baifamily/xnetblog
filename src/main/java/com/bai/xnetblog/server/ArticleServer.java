package com.bai.xnetblog.server;

import com.bai.xnetblog.Utils.CurrentUser;
import com.bai.xnetblog.dao.ArticleMapper;
import com.bai.xnetblog.dao.TagsMapper;
import com.bai.xnetblog.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class ArticleServer {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    TagsMapper tagsMapper;



    public List<Article> getArticleAll(int page,int count) {
        int start = (page - 1) * count;
        return articleMapper.getArticleAll(-2,start, count,CurrentUser.getCurrentUser().getId(),null);
    }

    public int getArticleTotal(){
        return articleMapper.getArticleTotal();
    }

    public int deleteArticle(Long[] aids, int state) {
        if (state == 2) {
            return articleMapper.deleteArticle(aids);
        } else {
            return articleMapper.retreatState(aids,2);
        }
    }

    public int saveOrPublishArticle(Article article) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        article.setEditTime(timestamp);
        if (article.getState() == 1) {
            article.setPublishDate(timestamp);
        }

        int i = 0;
        if (article.getId() == -1) {
            article.setUid(CurrentUser.getCurrentUser().getId());
            i = articleMapper.addNewArticle(article);
        } else {
            i = articleMapper.updateArticle(article);
        }
        String[] dynamicTags = article.getDynamicTags();
        if (dynamicTags != null && dynamicTags.length != 0) {
            int target = addArticleTarget(dynamicTags, article.getId());
            if (target == -1) {
                return target;
            }
        }
        return i;
    }

    public int addArticleTarget(String[] dynamicTags, Long id) {
        //1.删除该文章目前所有的标签
        tagsMapper.deleteTagsByAid(id);
        //2.将上传上来的标签全部存入数据库
        tagsMapper.saveTags(dynamicTags);
        //3.查询这些标签的id
        List<Long> tIds = tagsMapper.getTagsIdByTagName(dynamicTags);
        //4.重新给文章设置标签
        int i = tagsMapper.saveTags2ArticleTags(tIds, id);
        return i == dynamicTags.length ? i : -1;
    }

    public List<Article> getAllArticle(Integer state, Integer page, Integer count, String keyword) {
        int start = (page - 1) * count;
        Long uid = CurrentUser.getCurrentUser().getId();
        return articleMapper.getArticleAll(state, start, count,uid, keyword);
    }

    public int getAllArticleTotal(Integer state, String keywords) {
        Long uid = CurrentUser.getCurrentUser().getId();
        return articleMapper.getArticleAllTotal(state, uid, keywords);
    }

    public Article getArticleById(Long aid) {
        return articleMapper.getArticleById(aid);
    }

    public int dustbinArticle(Long[] aids, Integer state) {
        if (state == 2) {
            return articleMapper.deleteArticle(aids);
        } else {
            return articleMapper.dustbinArticle(aids, 2);
        }
    }

    public int restoreArticle(Integer articleId) {
        return articleMapper.restoreArticle(articleId);
    }

    public List<String> dataStatisticsTime() {
        return articleMapper.dataStatisticsTime(CurrentUser.getCurrentUser().getId());
    }

    public List<Integer> dataStatisticsPV() {
        return articleMapper.dataStatisticsPV(CurrentUser.getCurrentUser().getId());
    }
}

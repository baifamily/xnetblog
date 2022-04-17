package com.bai.xnetblog.controller;


import com.bai.xnetblog.Utils.CurrentUser;
import com.bai.xnetblog.Utils.DateTypeHandler;
import com.bai.xnetblog.pojo.Article;
import com.bai.xnetblog.pojo.RespBean;
import com.bai.xnetblog.server.ArticleServer;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleServer articleServer;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public RespBean saveOrPublishArticle(@RequestParam("article") Article article) {
        if (articleServer.saveOrPublishArticle(article) == 1) {
            return new RespBean("success", article.getId() + "");
        } else {
            return new RespBean("error", article.getState() == 0 ? "文章保存失败" : "文章发表失败");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public RespBean updateImg(HttpServletRequest req, MultipartFile image) {
        StringBuffer url = new StringBuffer();
        //http://localhost:8081/blogimg/20210803/2da4f9ac-cf21-47c7-b26e-9d40395a1663
        String filePath = "/blogimg/" + sdf.format(new Date());
        String imgFolderPath = req.getServletContext().getRealPath(filePath);
        String requestURI = req.getRequestURI();
        String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll("", "");
        url.append(requestURI).append("/").append(filePath).append("/").append(imgName);
        File imgFolder = new File(imgFolderPath);//文件夹
        if (!imgFolder.exists()) {
            imgFolder.mkdirs();
        }
        try {
            IOUtils.write(image.getBytes(), new FileOutputStream(new File(imgFolder, imgName)));
            return new RespBean("success", url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RespBean("error", "上传失败");
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String,Object> getArticle(@RequestParam(value = "state", defaultValue = "-1") Integer state, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count,String keywords) {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("totalCount",articleServer.getAllArticleTotal(state, keywords));
        objectObjectHashMap.put("articles", articleServer.getAllArticle(state, page, count, keywords));
        return objectObjectHashMap;
    }

    @RequestMapping(value = "/{aid}", method = RequestMethod.GET)
    public Article getArticleById(@PathVariable Long aid) {
        return articleServer.getArticleById(aid);
    }

    @RequestMapping(value = "/dustbin", method = RequestMethod.PUT)
    public RespBean dustbinArticle(@RequestParam("aid") Long[] aids, @RequestParam("state") Integer state) {
        if (articleServer.dustbinArticle(aids, state) == 1) {
            return new RespBean("success", "更新成功");
        } else {
            return new RespBean("error", "更新失败");
        }
    }

    @RequestMapping(value = "/restore", method = RequestMethod.PUT)
    public RespBean restoreArticle(@RequestParam("articleId") Integer articleId) {
        if (articleServer.restoreArticle(articleId) == 1) {
            return new RespBean("success", "还原成功");
        } else {
            return new RespBean("error", "还原失败");
        }
    }

    @RequestMapping("/dataStatistics")
    public Map dataStatisticsArticle() {
        Map<String, Object> map = new HashMap<>();
        map.put("categories", articleServer.dataStatisticsTime());
        map.put("ds", articleServer.dataStatisticsPV());
        return map;
    }
}

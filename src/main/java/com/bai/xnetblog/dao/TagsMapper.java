package com.bai.xnetblog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagsMapper {

    int deleteTagsByAid(Long aid);

    int saveTags(@Param("tags") String[] dynamicTags);

    List<Long> getTagsIdByTagName(@Param("tagNames") String[] dynamicTags);

    int saveTags2ArticleTags(@Param("tagIds") List<Long> dynamicTags, @Param("aid") Long aid);


}

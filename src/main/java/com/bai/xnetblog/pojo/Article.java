package com.bai.xnetblog.pojo;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;//注意
import java.util.List;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Generated
@Getter
@Setter
public class Article {

    private Long id;
    private String title;
    private String mdContent;
    private String htmlContent;
    private String summary;
    private Long cid;
    private Long uid;
    private Timestamp publishDate;
    private Timestamp editTime;
    private int state;
    private int pageView;
    private String[] dynamicTags;
    private List<Tag> tags;


}

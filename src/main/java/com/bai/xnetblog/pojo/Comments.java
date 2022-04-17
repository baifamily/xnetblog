package com.bai.xnetblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comments {
    private Long id;
    private Long aid;
    private Long uid;
    private String content;
    private Timestamp publishDate;
    private Long prentId;
    private int apple;
}

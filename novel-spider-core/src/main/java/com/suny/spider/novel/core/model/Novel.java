package com.suny.spider.novel.core.model;

import lombok.*;

import java.util.Date;

/**
 * 小说实体模型
 *
 * @author sunjianrong
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Novel {
    private Long id;

    private String name;

    private String author;

    private String url;

    private String type;

    private String lastUpdateChapter;

    private String lastUpdateChapterUrl;

    private Integer status;

    private String firstLetter;

    private Integer platformId;

    private Date addTime;

    private Date lastUpdateTime;


    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }


    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }


    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }


    public void setLastUpdateChapter(String lastUpdateChapter) {
        this.lastUpdateChapter = lastUpdateChapter == null ? null : lastUpdateChapter.trim();
    }


    public void setLastUpdateChapterUrl(String lastUpdateChapterUrl) {
        this.lastUpdateChapterUrl = lastUpdateChapterUrl == null ? null : lastUpdateChapterUrl.trim();
    }


    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter == null ? null : firstLetter.trim();
    }

}
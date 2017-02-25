package com.suny.entites;

import java.io.Serializable;
import java.util.Date;

/**
 * Comments:   小说实体
 * Author:   孙建荣
 * Create Date: 2017/02/25 10:34
 */
public class Novel implements Serializable {

    private String name;                    // 书名
    private String auth;                    //作者名
    private String url;                     //小说的链接
    private String type;                    //小说的类别
    private String lastUpdateChapter;       //最后一章的章节名
    private String lastUpdateChapterUrl;     // 最后一章的链接
    private Date lastUpdateTime;            //小说最后更新的时间
    private int status;                     //小说的状态
    private char firstLetter;               //书名的首字母
    private int platformId;                 //小说平台的id
    private Date addTime;                   //这本小说放入我们数据库的时间

    public Novel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastUpdateChapter() {
        return lastUpdateChapter;
    }

    public void setLastUpdateChapter(String lastUpdateChapter) {
        this.lastUpdateChapter = lastUpdateChapter;
    }

    public String getLastUpdateChapterUrl() {
        return lastUpdateChapterUrl;
    }

    public void setLastUpdateChapterUrl(String lastUpdateChapterUrl) {
        this.lastUpdateChapterUrl = lastUpdateChapterUrl;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public char getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(char firstLetter) {
        this.firstLetter = firstLetter;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "Novel{" +
                "name='" + name + '\'' +
                ", auth='" + auth + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", lastUpdateChapter='" + lastUpdateChapter + '\'' +
                ", lastUpdateChapterUrl='" + lastUpdateChapterUrl + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", status=" + status +
                ", firstLetter=" + firstLetter +
                ", platformId=" + platformId +
                ", addTime=" + addTime +
                '}';
    }
}

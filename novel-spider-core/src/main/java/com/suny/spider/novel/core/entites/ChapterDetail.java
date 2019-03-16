package com.suny.spider.novel.core.entites;

import java.io.Serializable;

/**
 * Comments:  每个章节的文本详细内容
 *
 * @author 孙建荣
 * @date 2017/02/20 19:57
 */
public class ChapterDetail implements Serializable {

    private String title;             //章节标题
    private String content;           //章节正文
    private String prev;             //上一页的url地址
    private String next;             //下一页的url地址
    private int PrevStatus;       //是否有上一页
    private int NextStatus;       //是否有上一页


    public ChapterDetail() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChapterDetail that = (ChapterDetail) o;

        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (next != null ? !next.equals(that.next) : that.next != null) return false;
        if (prev != null ? !prev.equals(that.prev) : that.prev != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (prev != null ? prev.hashCode() : 0);
        result = 31 * result + (next != null ? next.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ChapterDetail{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", prev='" + prev + '\'' +
                ", next='" + next + '\'' +
                '}';
    }
}

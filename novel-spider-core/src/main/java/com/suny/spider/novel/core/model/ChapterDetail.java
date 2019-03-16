package com.suny.spider.novel.core.model;

import lombok.*;

import java.io.Serializable;

/**
 * 每个章节的文本详细内容
 *
 * @author sunjianrong
 * @date 2017/02/20 19:57
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ChapterDetail implements Serializable {

    /**
     * 章节标题
     */
    private String title;
    /**
     * 章节正文
     */
    private String content;
    /**
     * 上一页的url地址
     */
    private String prev;
    /**
     * 下一页的url地址
     */
    private String next;
    /**
     * 是否有上一页
     */
    private int PrevStatus;
    /**
     * 是否有上一页
     */
    private int NextStatus;


}

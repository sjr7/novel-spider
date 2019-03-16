package com.suny.spider.novel.core.model;

import lombok.*;

import java.io.Serializable;


/**
 * 一个章节对应的实体类，包含章节标题，章节所对应的url地址
 *
 * @author sunjianrong
 * @date 2017/02/19 10:36
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Chapter implements Serializable {

    private static final long serialVersionUID = 6310806020990323796L;

    /**
     * 章节标题
     */
    private String title;

    /**
     * 章节所对应的url地址
     */
    private String url;

}

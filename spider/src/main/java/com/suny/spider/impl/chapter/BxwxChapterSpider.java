package com.suny.spider.impl.chapter;

import com.suny.spider.entites.Chapter;
import com.suny.spider.enums.NovelSiteEnum;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Comments:      继承了小说章节爬虫，用于下载笔下文学的小说
 * Author:         孙建荣
 * Create Date:    2017/02/21 21:57
 */
public class BxwxChapterSpider extends AbstractChapterSpider {

    /**
     *   传入一个小说的url得到小说的章节
     * @param url  小说的url地址
     * @return     得到小说章节的List
     */
    public List<Chapter> getsChapter(String url) {
        List<Chapter> chapters = super.getChapter(url);


        return chapters;
    }







}

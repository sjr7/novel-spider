package com.suny.spider.novel.core.impl.chapter;

import com.suny.spider.novel.core.model.Chapter;

import java.util.List;

/**
 * 继承了小说章节爬虫，用于下载笔下文学的小说
 *
 * @author sunjianrong
 * @date 2017/02/21 21:57
 */
public class BxwxChapterSpider extends AbstractChapterSpider {

    /**
     * 传入一个小说的url得到小说的章节
     *
     * @param url 小说的url地址
     * @return 得到小说章节的List
     */
    public List<Chapter> getsChapter(String url) {
        return super.getChapter(url);
    }


}

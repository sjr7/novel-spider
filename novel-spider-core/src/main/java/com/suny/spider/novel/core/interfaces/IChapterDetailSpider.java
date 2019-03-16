package com.suny.spider.novel.core.interfaces;

import com.suny.spider.novel.core.model.ChapterDetail;

/**
 * 获取章节信息接口
 *
 * @author sunjianrong
 * @date 2017/02/20 20:01
 */
public interface IChapterDetailSpider {

    /**
     * 给定一个url，返回对应网站的内容
     *
     * @param url 小说的url地址
     * @return 小说章节信息
     */
    ChapterDetail getChapterDetail(String url);
}

package com.suny.interfaces;

import com.suny.entites.ChapterDetail;

/**
 * 获取章节信息接口
 * 孙建荣
 * 2017/02/20 20:01
 */
public interface IChapterDetailSpider {

    /**
     * 给定一个url，返回对应网站的内容
     * @param url
     * @return
     */
    public ChapterDetail getChapterDetail(String url);
}

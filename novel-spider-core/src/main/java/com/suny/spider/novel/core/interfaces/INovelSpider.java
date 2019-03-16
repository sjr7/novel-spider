package com.suny.spider.novel.core.interfaces;

import com.suny.spider.novel.core.model.Novel;

import java.util.Iterator;
import java.util.List;

/**
 * 爬取小说站点的小说列表
 *
 * @author sunjianrong
 * @date 2017/02/25 10:44
 */
public interface INovelSpider {


    /**
     * 抓取某一个页面时最大的尝试次数3
     */
    int MAX_TRY_TIMES = 3;

    /**
     * 判断是否有下一页
     *
     * @return true 为代表有下一页 false为最后一页
     */
    boolean hasNext();

    /**
     * 下一页的值
     *
     * @return 下一页的url地址
     */
    String next();

    Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes);

    /**
     * 传递一个url，返回一堆的小说的小说实体
     *
     * @param url url
     * @return 小说集合
     */
    List<Novel> getsNovel(String url, Integer maxTryTimes);


}

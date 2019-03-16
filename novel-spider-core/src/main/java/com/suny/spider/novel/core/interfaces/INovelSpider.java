package com.suny.spider.novel.core.interfaces;

import com.suny.spider.novel.core.entites.Novel;

import java.util.Iterator;
import java.util.List;

/**
 * Comments:   爬取小说站点的小说列表
 *
 * @author 孙建荣
 * @date 2017/02/25 10:44
 */
public interface INovelSpider {


    /**
     * 抓取某一个页面时最大的尝试次数3
     */
    public static final int MAX_TRY_TIMES = 3;

    /**
     * 判断是否有下一页
     *
     * @return
     */
    public boolean hasNext();

    /**
     * 下一页的值
     *
     * @return
     */
    public String next();

    public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes);

    /**
     * 传递一个url，返回一堆的小说的小说实体
     *
     * @param url url
     * @return
     */
    public List<Novel> getsNovel(String url, Integer maxTryTimes);


}

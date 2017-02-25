package com.suny.interfaces;

import com.suny.entites.Novel;

import java.util.List;

/**
 * Comments:   爬取小说站点的小说列表
 * Author:   孙建荣
 * Create Date: 2017/02/25 10:44
 */
public interface INovelSpider {


    /**  抓取某一个页面时最大的尝试次数3  */
    public static final int MAX_TRY_TIMES = 3;

    /**
     *  传递一个url，返回一堆的小说的小说实体
     * @param url   url
     * @return
     */
    public List<Novel> getsNovel(String url);


}

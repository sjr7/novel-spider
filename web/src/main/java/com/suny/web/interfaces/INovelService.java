package com.suny.web.interfaces;

import com.suny.spider.entites.Novel;

import java.util.List;

/**
 * Comments:
 * Author:   孙建荣
 * Create Date: 2017/02/28 16:16
 */
public interface INovelService {

    /**
     *  通过关键字查询结果，然后返回内容
     * @param keyword  查询的关键字
     * @return
     */
    public List<Novel> getsNovelByKeyword(String keyword);

    /**
     *  查找小说在指定的平台下
     * @param keyword   指定的关键字
     * @param platformId  平台id
     * @return
     */
    public List<Novel> getsNovelByKeyword(String keyword, int platformId);
}

package com.suny.spider.novel.web.service;

import com.suny.spider.novel.core.model.Novel;

import java.util.List;

/**
 * @author sunjianrong
 * @date 2017/02/28 16:16
 */
public interface INovelService {

    /**
     * 输入关键词自动补全
     *
     * @param keyword 要搜索的关键词
     * @return 自动提示的词语
     */
    List<Novel> getAutoCompletion(String keyword);

    /**
     * 通过关键字查询结果，然后返回内容
     *
     * @param keyword 查询的关键字
     * @return 关键词结果
     */
    List<Novel> getsNovelByKeyword(String keyword);

    /**
     * 查找小说在指定的平台下
     *
     * @param keyword    指定的关键字
     * @param platformId 平台id
     * @return 指定平台下的小说
     */
    List<Novel> getsNovelByKeyword(String keyword, int platformId);
}

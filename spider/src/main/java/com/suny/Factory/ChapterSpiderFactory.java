package com.suny.Factory;

import com.suny.enums.NovelSiteEnum;
import com.suny.impl.BxwxChapterSpider;
import com.suny.impl.DefaultChapterSpider;
import com.suny.interfaces.IChapterSpider;

/**
 * Comments:   章节爬虫工厂代理类
 * Author:      孙建荣
 * Create Date: 2017/02/21 22:05
 */
public class ChapterSpiderFactory {

    private ChapterSpiderFactory() {
    }

    /**
     * 通过给定的url，返回一个实现了IChapterSpider的实现类
     *
     * @param url 下载小说的地址
     * @return
     */
    public static IChapterSpider getChapterSpider(String url) {
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        IChapterSpider chapterSpider = null;
        switch (novelSiteEnum) {
            case Bxwx:
                chapterSpider = new BxwxChapterSpider();
                break;
            case DingDianXiaoShuo:
            case BiQuGe:
            case KanShuZhong:
                chapterSpider = new DefaultChapterSpider();
                break;

        }
        return chapterSpider;
    }
}











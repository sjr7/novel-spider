package com.suny.spider.novel.core.factory;

import com.suny.spider.novel.core.enums.NovelSiteEnum;
import com.suny.spider.novel.core.impl.chapter.BxwxChapterSpider;
import com.suny.spider.novel.core.impl.chapter.DefaultChapterSpider;
import com.suny.spider.novel.core.interfaces.IChapterSpider;

/**
 * 章节爬虫工厂代理类
 *
 * @author sunjianrong
 * @date 2017/02/21 22:05
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
            default:
                chapterSpider = new DefaultChapterSpider();
                break;

        }
        return chapterSpider;
    }
}











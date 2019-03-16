package com.suny.spider.novel.core.factory;

import com.suny.spider.novel.core.enums.NovelSiteEnum;
import com.suny.spider.novel.core.impl.novel.BxwxNovelSpider;
import com.suny.spider.novel.core.impl.novel.KanShuZhongNovelSpider;
import com.suny.spider.novel.core.interfaces.INovelSpider;

/**
 * 小说书籍的实现类
 *
 * @author sunjianrong
 * @date 2017/02/25 13:24
 */
public final class NovelSpiderFactory {
    public NovelSpiderFactory() {
    }

    /**
     * 小说的列表工厂代理方法
     *
     * @param url 小说url地址。
     * @return
     */
    public static INovelSpider getNovelSpider(String url) {
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        switch (novelSiteEnum) {
            case Bxwx:
                return new BxwxNovelSpider();
            case KanShuZhong:
                return new KanShuZhongNovelSpider();
            default:
                throw new RuntimeException(url + "暂时不被支持");
        }
    }
}

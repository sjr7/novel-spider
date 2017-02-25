package com.suny.Factory;

import com.suny.enums.NovelSiteEnum;
import com.suny.impl.novel.BxwxNovelSpider;
import com.suny.impl.novel.KanShuZhongNovelSpider;
import com.suny.interfaces.INovelSpider;

/**
 * Comments:  小说书籍的实现类
 * Author:   孙建荣
 * Create Date: 2017/02/25 13:24
 */
public final  class NovelSpiderFactory {
    public NovelSpiderFactory() {
    }

    /**
     *  小说的列表工厂代理方法
     * @param url
     * @return
     */
    public static INovelSpider getNovelSpider(String url) {
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        switch (novelSiteEnum){
            case Bxwx:
                return new BxwxNovelSpider();
            case KanShuZhong:
                return new KanShuZhongNovelSpider();
            default:
                throw new RuntimeException(url + "暂时不被支持");
        }
    }
}

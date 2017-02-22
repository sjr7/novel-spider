package com.suny.Factory;

import com.suny.enums.NovelSiteEnum;
import com.suny.impl.DefaultChapterDetailSpider;
import com.suny.interfaces.IChapterDetailSpider;

/**
 * 孙建荣
 * 2017/02/21 22:24
 */
public final class ChapterDetailSpiderFactory {
    private ChapterDetailSpiderFactory() {
    }

    /**
     * 根据指定的url拿到实现IChapterDetailSpider的实现类
     * @param url
     * @return
     */
    public static IChapterDetailSpider getChapterDetailSpider(String url) {
        IChapterDetailSpider spider = null;
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        switch ( novelSiteEnum){
            case DingDianXiaoShuo :
            case BiQuGe :
            case KanShuZhong :
            case Bxwx :
                spider = new DefaultChapterDetailSpider();
                break;
        }
        return spider;
        }

}

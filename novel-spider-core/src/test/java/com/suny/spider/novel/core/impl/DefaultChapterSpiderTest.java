package com.suny.spider.novel.core.impl;

import com.suny.spider.novel.core.config.Configuration;
import com.suny.spider.novel.core.enums.NovelSiteEnum;
import com.suny.spider.novel.core.impl.chapter.DefaultChapterDetailSpider;
import com.suny.spider.novel.core.impl.download.NovelDownload;
import com.suny.spider.novel.core.interfaces.IChapterDetailSpider;
import com.suny.spider.novel.core.interfaces.INovelDownload;
import com.suny.spider.novel.core.utils.NovelSpiderUtil;
import junit.framework.TestCase;

public class DefaultChapterSpiderTest extends TestCase {

    public void testDownload() {
        INovelDownload download = new NovelDownload();
        Configuration config = new Configuration();
        config.setPath("/home/sunjianrong/Downloads");
        config.setSize(100);
        download.download("http://www.biquge.tw/0_5", config);
    }


    public void testChapterDetail() {

        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        System.out.println(spider.getChapterDetail("http://www.biquge.tw/0_5/1374.html").getContent());
    }


    public void testGetSiteContext() {
        System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("http://www.biquge.tw/0_5/")));
    }


}
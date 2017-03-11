package com.suny.spider.impl;

import com.suny.spider.configuration.Configuration;
import com.suny.spider.enums.NovelSiteEnum;
import com.suny.spider.impl.download.NovelDownload;
import com.suny.spider.interfaces.INovelDownload;
import com.suny.spider.utils.NovelSpiderUtil;
import junit.framework.TestCase;

public class DefaultChapterSpiderTest extends TestCase {

    public void testDownload() {
        INovelDownload download = new NovelDownload();
        Configuration config = new Configuration();
        config.setPath("D:/1");
        config.setSize(100);
        download.download("http://www.biquge.tw/0_5", config);
    }


    public void testChapterDetail() {

   //     IChapterDetailSpider spider = new DefaultChapterDetailSpider();
    //    System.out.println(spider.getChapterDetail("http://www.biquge.tw/0_5/1374.html").getContent());
    }


    public void testGetSiteContext() {
        System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("http://www.biquge.tw/0_5/")));
    }





}
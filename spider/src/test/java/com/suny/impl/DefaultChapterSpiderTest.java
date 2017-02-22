package com.suny.impl;

import com.suny.configuration.Configuration;
import com.suny.entites.Chapter;
import com.suny.enums.NovelSiteEnum;
import com.suny.interfaces.IChapterDetailSpider;
import com.suny.interfaces.IChapterSpider;
import com.suny.interfaces.INovelDownload;
import com.suny.utils.NovelSpiderUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void test() {
        IChapterSpider chapterSpider = new DefaultChapterSpider();
        System.out.println("start visit website");
        List<Chapter> chapterList = chapterSpider.getChapter("http://www.biquge.tw/0_5/");
        for (Chapter chapter : chapterList) {
            System.out.println(chapter);
        }

    }

}
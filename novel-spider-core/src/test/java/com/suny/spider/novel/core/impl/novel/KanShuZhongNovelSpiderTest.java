package com.suny.spider.novel.core.impl.novel;

import com.suny.spider.novel.core.factory.NovelSpiderFactory;
import com.suny.spider.novel.core.interfaces.INovelSpider;
import com.suny.spider.novel.core.model.Novel;
import junit.framework.TestCase;

import java.util.List;


public class KanShuZhongNovelSpiderTest extends TestCase {

    public void testKanShuZhongNovelSpiderTest1() {
        INovelSpider spider = NovelSpiderFactory.getNovelSpider("http://www.kanshuzhong.com/map/A/1/");
        List<Novel> novelList = spider.getsNovel("http://www.kanshuzhong.com/map/A/1/", 10);
        for (Novel novel : novelList) {
            System.out.println(novel);
        }

    }


}
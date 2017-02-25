package com.suny.impl.novel;

import com.suny.Factory.NovelSpiderFactory;
import com.suny.entites.Novel;
import com.suny.interfaces.INovelSpider;
import junit.framework.TestCase;

import java.util.List;


public class KanShuZhongNovelSpiderTest extends TestCase{

    public void testKanShuZhongNovelSpiderTest1() {
        INovelSpider spider = NovelSpiderFactory.getNovelSpider("http://www.kanshuzhong.com/map/A/1/");
        List<Novel> novelList = spider.getsNovel("http://www.kanshuzhong.com/map/A/1/");
        for (Novel novel : novelList) {
            System.out.println(novel);
        }

    }


    
}
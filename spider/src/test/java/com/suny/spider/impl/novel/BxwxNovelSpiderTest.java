package com.suny.spider.impl.novel;

import com.suny.spider.Factory.NovelSpiderFactory;
import com.suny.spider.entites.Novel;
import com.suny.spider.interfaces.INovelSpider;
import org.junit.Test;

import java.util.List;

/**
 * 笔下文学小说爬取测试
 */
public class BxwxNovelSpiderTest {

    @Test
    public void testBxwxGetsNovel() {
        INovelSpider spider = NovelSpiderFactory.getNovelSpider("http://www.bxwx9.org/binitialE/0/1.htm");
        List<Novel> novels = spider.getsNovel("http://www.bxwx9.org/binitialE/0/1.htm", 10);
        for (Novel novel : novels) {
            System.out.println(novel);
        }
    }
    
}
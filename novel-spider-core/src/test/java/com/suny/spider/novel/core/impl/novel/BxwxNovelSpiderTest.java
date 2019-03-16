package com.suny.spider.novel.core.impl.novel;

import com.suny.spider.novel.core.factory.NovelSpiderFactory;
import com.suny.spider.novel.core.interfaces.INovelSpider;
import com.suny.spider.novel.core.model.Novel;
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
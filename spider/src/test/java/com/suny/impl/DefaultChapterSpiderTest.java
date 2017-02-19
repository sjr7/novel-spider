package com.suny.impl;

import com.suny.entites.Chapter;
import com.suny.interfaces.IChapterSpider;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class DefaultChapterSpiderTest extends TestCase {

    @Test
    public void test(){
        IChapterSpider chapterSpider=new AbstractChapterSpider();
        System.out.println("start visit website");
        List<Chapter> chapterList = chapterSpider.getChapter("http://www.biquge.tw/0_5");
        for (Chapter chapter: chapterList){
            System.out.println(chapter);
        }

    }

}
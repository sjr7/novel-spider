package com.suny.spider.novel.core.impl.chapter;

import com.suny.spider.novel.core.entites.Chapter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BxwxChapterSpiderTest {

    @Test
    public void compare() {

        Chapter chapter1 = new Chapter();
        Chapter chapter2 = new Chapter();
        Chapter chapter3 = new Chapter();
        Chapter chapter4 = new Chapter();

        List<Chapter> chapters = new ArrayList<Chapter>();


        chapter3.setTitle("第三章 ");
        chapter3.setUrl("http://www.bxwx9.org/b/117/117767/20569487.html");
        chapter2.setTitle("第二章 ");
        chapter2.setUrl("http://www.bxwx9.org/b/117/117767/20569486.html");

        chapter4.setTitle("第四章 ");
        chapter4.setUrl("http://www.bxwx9.org/b/117/117767/20569488.html");
        chapter1.setTitle("第一章 ");
        chapter1.setUrl("http://www.bxwx9.org/b/117/117767/20569485.html");


        chapters.add(chapter2);
        chapters.add(chapter3);
        chapters.add(chapter1);
        chapters.add(chapter4);

        Collections.sort(chapters, new Comparator<Chapter>() {
            @Override
            public int compare(Chapter o1, Chapter o2) {
                String o1Url = o1.getUrl();
                String o2Url = o2.getUrl();

                String o1Index = o1Url.substring(o1Url.lastIndexOf("/") + 1,
                        o1Url.lastIndexOf("."));
                String o2Index = o2Url.substring(o2Url.lastIndexOf("/") + 1,
                        o2Url.lastIndexOf("."));

                return o1Index.compareTo(o2Index);

            }
        });

        for (Chapter chapter : chapters) {
            System.out.println(chapter.toString());
        }
    }

}
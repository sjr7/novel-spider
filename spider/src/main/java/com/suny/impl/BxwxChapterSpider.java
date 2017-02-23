package com.suny.impl;

import com.suny.entites.Chapter;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Comments:      继承了小说章节爬虫，用于下载笔下文学的小说
 * Author:         孙建荣
 * Create Date:    2017/02/21 21:57
 */
public class BxwxChapterSpider extends AbstractChapterSpider {

    /**
     *   传入一个小说的url得到小说的章节
     * @param url  小说的url地址
     * @return     得到小说章节的List
     */
    public List<Chapter> getsChapter(String url) {
        List<Chapter> chapters = super.getChapter(url);
        Collections.sort(chapters, new Comparator<Chapter>() {
            @Override
            public int compare(Chapter o1, Chapter o2) {
                String o1Url = o1.getUrl();
                String o2Url = o2.getUrl();
                String o1Index = o1Url.substring(o1Url.lastIndexOf("/") + 1,
                        o1Url.lastIndexOf("."));
                String o2Index = o1Url.substring(o2Url.lastIndexOf("/") + 1,
                        o2Url.lastIndexOf("."));

                return o1Index.compareTo(o2Index);
            }
        });

        return chapters;
    }

}

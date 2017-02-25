package com.suny.impl.chapter;

import com.suny.entites.Chapter;
import com.suny.enums.NovelSiteEnum;
import com.suny.impl.AbstractSpider;
import com.suny.interfaces.IChapterSpider;
import com.suny.utils.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Comments:     爬虫抽象类的子类，实现了IChapterSpider接口
 * Author:       孙建荣
 * Create Date:   2017/02/19 14:38
 */
public abstract class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {


    /**
     * @param url 要趴取的页面
     * @return 一本小说的章节集合
     */
    @Override
    public List<Chapter> getChapter(String url) {
        try {
            String result = this.crwal(url);
            Document document = Jsoup.parse(result);   //解析页面数据
            document.setBaseUri(url);

            // 得到一大堆的element标签 ，然后充里面选择一个合适的css选择器。。。。
            Elements elements = document.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url))
                    .get("chapter-list-selector"));

            //  用一个List来存放小说章节对应的标题，文本内容等等。。。。
            List<Chapter> chapters = new ArrayList<>();
            for (Element e : elements) {
                Chapter chapter = new Chapter();
                chapter.setTitle(e.text());                    // 把标题放进去
                chapter.setUrl(e.absUrl("href"));       // 把url地址放进去
                chapters.add(chapter);               // 把一个个的章节放到一个章节List里面去
            }
            return chapters;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}























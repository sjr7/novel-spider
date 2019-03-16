package com.suny.spider.novel.core.impl.chapter;

import com.suny.spider.novel.core.entites.Chapter;
import com.suny.spider.novel.core.enums.NovelSiteEnum;
import com.suny.spider.novel.core.impl.AbstractSpider;
import com.suny.spider.novel.core.interfaces.IChapterSpider;
import com.suny.spider.novel.core.utils.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Comments:     爬虫抽象类的子类，实现了IChapterSpider接口
 *
 * @author 孙建荣
 * @date 2017/02/19 14:38
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

            //笔下文学故意对顺序进行搞乱，我们这里重新排序
            if (NovelSiteEnum.getEnumByUrl(url).getUrl().equals("bxwx9.org")) {

                Collections.sort(chapters, new Comparator<Chapter>() {
                    //对小说的章节进行排序
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
            }

            return chapters;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}























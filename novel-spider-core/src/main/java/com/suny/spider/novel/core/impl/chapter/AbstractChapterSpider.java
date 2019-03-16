package com.suny.spider.novel.core.impl.chapter;

import com.suny.spider.novel.core.enums.NovelSiteEnum;
import com.suny.spider.novel.core.impl.AbstractSpider;
import com.suny.spider.novel.core.interfaces.IChapterSpider;
import com.suny.spider.novel.core.model.Chapter;
import com.suny.spider.novel.core.utils.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 爬虫抽象类的子类，实现了IChapterSpider接口
 *
 * @author sunjianrong
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
            Document document = Jsoup.parse(result);
            document.setBaseUri(url);

            // 得到一大堆的element标签 ，然后充里面选择一个合适的css选择器。。。。
            Elements elements = document.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url))
                    .get("chapter-list-selector"));

            //  用一个List来存放小说章节对应的标题，文本内容等等。。。。
            List<Chapter> chapters = new ArrayList<>();
            for (Element e : elements) {
                Chapter chapter = new Chapter();
                chapter.setTitle(e.text());
                chapter.setUrl(e.absUrl("href"));
                chapters.add(chapter);
            }

            //笔下文学故意对顺序进行搞乱，我们这里重新排序
            if ("bxwx9.org".equals(NovelSiteEnum.getEnumByUrl(url).getUrl())) {

                //对小说的章节进行排序
                chapters.sort((o1, o2) -> {
                    String o1Url = o1.getUrl();
                    String o2Url = o2.getUrl();
                    String o1Index = o1Url.substring(o1Url.lastIndexOf("/") + 1,
                            o1Url.lastIndexOf("."));
                    String o2Index = o2Url.substring(o2Url.lastIndexOf("/") + 1,
                            o2Url.lastIndexOf("."));
                    return o1Index.compareTo(o2Index);
                });
            }

            return chapters;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}























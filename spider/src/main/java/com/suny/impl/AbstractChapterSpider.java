package com.suny.impl;

import com.suny.entites.Chapter;
import com.suny.enums.NovelSiteEnum;
import com.suny.interfaces.IChapterSpider;
import com.suny.utils.NovelSpiderUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 孙建荣
 * 2017/02/19 14:38
 */
public abstract class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {



    @Override
    public List<Chapter> getChapter(String url) {
        try {
            String result = this.crwal(url);
            Document document = Jsoup.parse(result);   //解析页面数据
            document.setBaseUri(url);
            Elements elements = document.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter-list-selecttor"));
            List<Chapter> chapters = new ArrayList<>();
            for (Element e : elements) {
                Chapter chapter = new Chapter();
                chapter.setTitle(e.text());
                chapter.setUrl( e.absUrl("href"));
                chapters.add(chapter);
            }
            return chapters;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}























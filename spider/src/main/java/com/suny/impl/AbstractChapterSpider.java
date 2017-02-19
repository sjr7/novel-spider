package com.suny.impl;

import com.suny.entites.Chapter;
import com.suny.interfaces.IChapterSpider;
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
public class AbstractChapterSpider implements IChapterSpider {

    protected String crwal(String url) throws Exception {
        try (CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse httpResponse = closeableHttpClient.execute(new HttpGet(url))
        ) {
            //返回抓取的结果
            String result = EntityUtils.toString(httpResponse.getEntity());
            return result;
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Override
    public List<Chapter> getChapter(String url) {
        try {
            String result = this.crwal(url);
            Document document = Jsoup.parse(result);
            Elements elements = document.select("#list dd a");
            List<Chapter> chapters = new ArrayList<>();
            for (Element e: elements) {
                Chapter chapter = new Chapter();
                chapter.setTitle(e.text());
                chapter.setUrl(e.attr("href"));
                chapters.add(chapter);
            }
            return chapters;
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }
}























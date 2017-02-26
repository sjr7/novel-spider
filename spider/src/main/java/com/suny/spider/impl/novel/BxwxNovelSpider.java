package com.suny.spider.impl.novel;

import com.suny.spider.entites.Novel;
import com.suny.spider.enums.NovelSiteEnum;
import com.suny.spider.utils.NovelSpiderUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Comments: 笔下文学网站的书籍列表
 * Author:   孙建荣
 * Create Date: 2017/02/25 11:21
 */
public class BxwxNovelSpider extends AbstractNovelSpider {

    public BxwxNovelSpider() {
    }

    /**
     *   抓取小说的方法
     * @param url url   抓取的url地址
     * @param maxTryTimes   最大尝试次数
     * @return
     */
    @Override
    public List<Novel> getsNovel(String url, Integer maxTryTimes) {
        List<Novel> novelList = new ArrayList<>();
        try {
            Elements trs = super.getsTr(url, 2);    // 传入url解析tr标签列表
            for (int index = 1, size = trs.size(); index < size; index++) {
                Element tr = trs.get(index);    //获取tr标签集合中的对应下标集合
                Elements tds = tr.getElementsByTag("td");   // 获取一个tr标签中的td标签集合
                Novel novel = new Novel();
                novel.setName(tds.first().getElementsByTag("a").text());    // 获取标签对应文本>>>>>书名
                String novelUrl = tds.first().getElementsByTag("a").first()
                        .absUrl("href")
                        .replace(".htm", "/")
                        .replace("/binfo/", "/b/");
                novel.setUrl(novelUrl);
                novel.setLastUpdateChapter(tds.get(1).text());   //更新的最后一章节标题
                //拿到更新的最后一张的url地址
                novel.setLastUpdateChapterUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
                novel.setAuthor(tds.get(2).text());   //设置作者

                // 把最后更新的时间设置进去，然后网页上拿到的是字符串对象，转为Date对象
                novel.setLastUpdateTime(NovelSpiderUtil.getDate(tds.get(4).text(), "yy-MM-dd"));   //设置更新的时间
                novel.setStatus(NovelSpiderUtil.getNovelStatus(tds.get(5).text()));   //设置更新状态
                novel.setPlatformId(NovelSiteEnum.getEnumByUrl(url).getId());   //设置更新状态
                novelList.add(novel);    //把每一本小说放到小说集合里面去


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return novelList;
    }


}














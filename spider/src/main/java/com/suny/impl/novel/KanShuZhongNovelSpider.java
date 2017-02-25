package com.suny.impl.novel;

import com.suny.entites.Novel;
import com.suny.enums.NovelSiteEnum;
import com.suny.utils.NovelSpiderUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Comments:  看书中小说网站的爬取
 * Author:   孙建荣
 * Create Date: 2017/02/25 13:01
 */
public class KanShuZhongNovelSpider extends AbstractNovelSpider {
    @Override
    public List<Novel> getsNovel(String url) {
        List<Novel> novelList = new ArrayList<>();
        try {
            Elements trs = super.getsTr(url);    // 传入url解析tr标签列表
            for (int index = 1, size = trs.size(); index < size - 1; index++) {
                Element tr = trs.get(index);    //获取tr标签集合中的对应下标集合
                Elements tds = tr.getElementsByTag("td");   // 获取一个tr标签中的td标签集合
                Novel novel = new Novel();
                novel.setType(tds.first().getElementsByTag("span").first().text());   //设置小说所属类别
                novel.setName(tds.get(1).getElementsByTag("span").first().text());    // 获取标签对应文本>>>>>书名
                String novelUrl = tds.get(1).getElementsByTag("a").first()
                        .absUrl("href");
                novel.setUrl(novelUrl);
                novel.setLastUpdateChapter(tds.get(2).getElementsByTag("span").first().text());   //更新的最后一章节标题
                //拿到更新的最后一张的url地址
                novel.setLastUpdateChapterUrl(tds.get(2).getElementsByTag("a").first().absUrl("href"));
                novel.setAuth(tds.get(3).getElementsByTag("a").first().text());   //设置作者
                // 把最后更新的时间设置进去，然后网页上拿到的是字符串对象，转为Date对象
                novel.setLastUpdateTime(NovelSpiderUtil.getDate(tds.get(4).getElementsByTag("span").first().text(), "yy-MM-dd"));   //设置更新的时间
                novel.setStatus(NovelSpiderUtil.getNovelStatus(tds.get(5).getElementsByTag("span").first().text()));   //设置更新状态
                novel.setPlatformId(NovelSiteEnum.getEnumByUrl(url).getId());   //设置平台id
                novelList.add(novel);    //把每一本小说放到小说集合里面去


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return novelList;
    }
}


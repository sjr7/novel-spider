package com.suny.spider.impl.novel;

import com.suny.spider.entites.Novel;
import com.suny.spider.enums.NovelSiteEnum;
import com.suny.spider.impl.AbstractSpider;
import com.suny.spider.interfaces.INovelSpider;
import com.suny.spider.utils.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Comments:   抽象的小说列表抓取，应经实现了解析tr元素的方法
 * Author:   孙建荣
 * Create Date: 2017/02/25 10:53
 */
public abstract class AbstractNovelSpider extends AbstractSpider implements INovelSpider {

    @Override
    public boolean hasNext() {
        return !nextPage.isEmpty();
    }

    @Override
    public String next() {
        return nextPage;
    }

    @Override
    public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes) {
        nextPage = firstPage;

        return new NovelIterator(maxTryTimes);
    }

    /**
     * 下一页的标签元素
     */
    protected Element nextPageElement;

    /**
     * 下一页值
     */
    protected String nextPage;

    /**
     * 默认的抓取方法，最多会尝试 {@link INovelSpider#MAX_TRY_TIMES }
     *
     * @param url 解析的url地址
     * @return
     * @throws Exception
     */
    protected Elements getsTr(String url) throws Exception {
        return getsTr(url, INovelSpider.MAX_TRY_TIMES);
    }

    /**
     * 以指定的次数的形式去解析对应的网页
     *
     * @param url         解析的url地址
     * @param maxTryTimes 最大的尝试次数，为空则默认是 {@link INovelSpider#MAX_TRY_TIMES }
     * @return
     * @throws Exception
     */
    protected Elements getsTr(String url, Integer maxTryTimes) throws Exception {
        //  判断传入的maxTimes是否为空，为空则设置一个读取的默认尝试次数
        maxTryTimes = maxTryTimes == null ? INovelSpider.MAX_TRY_TIMES : maxTryTimes;
        Elements trs = null;     // 定义一个存储tr标签的的List
        for (int i = 0; i < maxTryTimes; i++) {
            try {
                String result = super.crwal(url);           // 解析url地址的内容
                Map<String, String> context = NovelSpiderUtil.getContext(
                        NovelSiteEnum.getEnumByUrl(url)
                );
                String novelSelector = context.get("novel-selector");     // 得到对应标签体的内容

                if (novelSelector == null) {
                    throw new RuntimeException(NovelSiteEnum.getEnumByUrl(url).getUrl() + "this url =" + url + "is not support ");
                }

                Document document = Jsoup.parse(result);
                document.setBaseUri(url);
                trs = document.select(novelSelector);      // 把选择到的tr标签保存到List
                //获取下一页的标签
                String nextPageSelector = context.get("novel-next-page-selector");
                if (nextPageSelector != null) {
                    // 得到一堆带有下一页css选择器的元素
                    Elements nextPageElements = document.select(nextPageSelector);
                    // 得到一个 元素、有就用第一个元素给赋值，没有就为空
                    nextPageElement = nextPageElements == null ? null : nextPageElements.first();
                    // 得到下一页的url地址
                    if (nextPageElement != null) {
                        nextPage = nextPageElement.absUrl("href");
                    } else {
                        nextPage = "";
                    }
                }

                return trs;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        throw new RuntimeException("this url >>>" + url + "trying fail by " + maxTryTimes);

    }

    /**
     *  一个迭代器，用户对小说的数据列表进行抓取
     */
    private class NovelIterator implements Iterator<List<Novel>> {

        private Integer maxTryTimes;

        public NovelIterator(Integer maxTryTimes) {
            this.maxTryTimes = maxTryTimes;
        }

        @Override
        public boolean hasNext() {
            return AbstractNovelSpider.this.hasNext();
        }

        @Override
        public List<Novel> next() {
            List<Novel> novelList = getsNovel(nextPage, maxTryTimes);
            return novelList;
        }
    }


}









package com.suny.spider.impl.chapter;

import com.suny.spider.entites.ChapterDetail;
import com.suny.spider.enums.NovelSiteEnum;
import com.suny.spider.impl.AbstractSpider;
import com.suny.spider.interfaces.IChapterDetailSpider;
import com.suny.spider.utils.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * Comments:  获取章节详细信息接口的抽象实现类
 * Author:       孙建荣
 * Create Date:  2017/02/20 20:04
 */
public abstract class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider {
    @Override
    public ChapterDetail getChapterDetail(String url) {
        try {
            String result = super.crwal(url);
            Document document = Jsoup.parse(result);    //解析得到的页面数据
            document.setBaseUri(url);           //设置网站根目录
            Map<String, String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));   //返回规则

            //得到标题的内容
            String titleSelector = context.get("chapter-detail-title-selector");    //获取标题选择器的规则
            String[] splits = titleSelector.split("\\,");    // 以逗号分割规则，java中\,为转义字符
            splits = parseSelector(splits);    //转换下规则
            ChapterDetail chapterDetail = new ChapterDetail();
            int titleSelectNumber = Integer.parseInt(splits[1]);     //标题选择器的下标，对应的第几个
            chapterDetail.setTitle(document.select(splits[0]).get(titleSelectNumber).text());


            //拿到章节的内容
            String contentSelector = context.get("chapter-detail-content-selector");     //获取章节选择器的内容
            chapterDetail.setContent(document.select(contentSelector).first().html());   //得到章节选择器的第一个下标的文本

            String urlEnum = NovelSiteEnum.getEnumByUrl(url).getUrl();
            //拿到前一章的地址
            String prevSelector = context.get("chapter-detail-prev-selector");    //得到前一章的地址选择器
            splits = prevSelector.split("\\,");    //切割规则
            splits = parseSelector(splits);      //  转换下规则
            //   首先选择规则为splits[0]中的内容，splits[0]为规则，splits[1]为规则对应的元素下标
            //  然后再获取下标内容的url地址
            String prevUrl = document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href");

            boolean hasPrevPage = prevUrl.contains(".html") && !prevUrl.contains("index.html");
            //对是否有上一页或者下一页的状态进行判断，有就设置，没有就不设置
            if (hasPrevPage) {
                chapterDetail.setPrev(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));

            } else {
                chapterDetail.setPrev(url);
            }

            //拿到后一章的地址
            String nextSelector = context.get("chapter-detail-next-selector");    //得到后一章的地址选择器
            splits = nextSelector.split("\\,");      //切割规则
            splits = parseSelector(splits);    //  转换下规则
            String nextUrl = document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href");
            boolean hasNextPage = nextUrl.contains(".html") && !nextUrl.contains("index.html");

            if (hasNextPage) {
                chapterDetail.setNext(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));

            } else {
                chapterDetail.setNext(url);

            }
            return chapterDetail;    //返回章节对应的信息

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 对拿到的css选择器规则进行转换
     *
     * @param splits css选择器数组，可能包含css选择器代码，以及选择器的下标
     * @return 转换过后的css选择器规则
     */
    private String[] parseSelector(String[] splits) {
        String[] newSplits = new String[2];       //新建一个大小为2的数组

        //如果在配置文件里面定义了下标就抓出去，直接使用，没有就自己定义一个咯。。。。。。
        if (splits.length == 1) {
            newSplits[0] = splits[0];           //这个是规则
            newSplits[1] = "0";             //  默认就抓取选择器的的第一个下标
            return newSplits;
        } else {
            return splits;
        }
    }

}









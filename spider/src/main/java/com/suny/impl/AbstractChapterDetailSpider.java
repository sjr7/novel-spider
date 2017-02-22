package com.suny.impl;

import com.suny.entites.ChapterDetail;
import com.suny.enums.NovelSiteEnum;
import com.suny.interfaces.IChapterDetailSpider;
import com.suny.utils.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * 获取章节详细信息接口的实现类
 * 孙建荣
 * 2017/02/20 20:04
 */
public abstract class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider {
    @Override
    public ChapterDetail getChapterDetail(String url) {
        try {
            String result = super.crwal(url);
            Document document = Jsoup.parse(result);    //解析
            document.setBaseUri(url);   //设置网站根目录
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
            chapterDetail.setContent(document.select(contentSelector).first().text());   //得到章节选择器的第一个下标的文本

            //拿到前一章的地址
            String prevSelector = context.get("chapter-detail-prev-selector");    //得到前一章的地址选择器
            splits = prevSelector.split("\\,");    //切割规则
            splits = parseSelector(splits);      //  转换下规则
            //   首先选择规则为splits[0]中的内容，splits[0]为规则，splits[1]为规则对应的元素下标
            //  然后再获取下标内容的url地址
            chapterDetail.setPrev(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));

            //拿到后一章的地址
            String nextSelector = context.get("chapter-detail-prev-selector");    //得到后一章的地址选择器
            splits = nextSelector.split("\\,");      //切割规则
            splits = parseSelector(splits);    //  转换下规则
            chapterDetail.setNext(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));


            return chapterDetail;    //返回章节对应的信息

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private String[] parseSelector(String[] splits) {
        String[] newSplits = new String[2];       //新建一个大小为2的数组
        //如果在配置文件里面定义了下标就抓出去
        if (splits.length == 1) {
            newSplits[0] = splits[0];   //这个是规则
            newSplits[1] = "0";    //  默认就抓取选择器的的第一个下标
            return newSplits;
        } else {
            return splits;
        }
    }

}









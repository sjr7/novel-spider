package com.suny.spider.novel.core.impl.chapter;

import com.suny.spider.novel.core.enums.NovelSiteEnum;
import com.suny.spider.novel.core.impl.AbstractSpider;
import com.suny.spider.novel.core.interfaces.IChapterDetailSpider;
import com.suny.spider.novel.core.model.ChapterDetail;
import com.suny.spider.novel.core.utils.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * 获取章节详细信息接口的抽象实现类
 *
 * @author sunjianrong
 * @date 2017/02/20 20:04
 */
public abstract class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider {
    @Override
    public ChapterDetail getChapterDetail(String url) {
        try {
            String result = super.crwal(url);
            //解析得到的页面数据
            Document document = Jsoup.parse(result);
            //设置网站根目录
            document.setBaseUri(url);
            //返回规则
            Map<String, String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));

            //得到标题的内容  获取标题选择器的规则
            String titleSelector = context.get("chapter-detail-title-selector");
            // 以逗号分割规则，java中\,为转义字符
            String[] splits = titleSelector.split(",");
            splits = parseSelector(splits);
            ChapterDetail chapterDetail = new ChapterDetail();

            //标题选择器的下标，对应的第几个
            int titleSelectNumber = Integer.parseInt(splits[1]);
            chapterDetail.setTitle(document.select(splits[0]).get(titleSelectNumber).text());


            //拿到章节的内容  获取章节选择器的内容
            String contentSelector = context.get("chapter-detail-content-selector");
            //得到章节选择器的第一个下标的文本
            chapterDetail.setContent(document.select(contentSelector).first().html());

            String urlEnum = NovelSiteEnum.getEnumByUrl(url).getUrl();
            //拿到前一章的地址
            //得到前一章的地址选择器
            String prevSelector = context.get("chapter-detail-prev-selector");
            //切割规则
            splits = prevSelector.split(",");
            //  转换下规则
            splits = parseSelector(splits);
            String prevUrl = document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href");

            boolean hasPrevPage = prevUrl.contains(".html") && !prevUrl.contains("index.html");
            //对是否有上一页或者下一页的状态进行判断，有就设置，没有就不设置
            if (hasPrevPage) {
                chapterDetail.setPrev(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));

            } else {
                chapterDetail.setPrev(url);
            }

            //拿到后一章的地址  得到后一章的地址选择器
            String nextSelector = context.get("chapter-detail-next-selector");
            splits = nextSelector.split(",");
            splits = parseSelector(splits);
            String nextUrl = document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href");
            boolean hasNextPage = nextUrl.contains(".html") && !nextUrl.contains("index.html");

            if (hasNextPage) {
                chapterDetail.setNext(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));

            } else {
                chapterDetail.setNext(url);

            }
            return chapterDetail;

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
        String[] newSplits = new String[2];

        //如果在配置文件里面定义了下标就抓出去，直接使用，没有就自己定义一个咯。。。。。。
        if (splits.length == 1) {
            //这个是规则
            newSplits[0] = splits[0];
            //  默认就抓取选择器的的第一个下标
            newSplits[1] = "0";
            return newSplits;
        } else {
            return splits;
        }
    }

}









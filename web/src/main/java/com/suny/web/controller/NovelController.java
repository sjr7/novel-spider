package com.suny.web.controller;

import com.suny.spider.Factory.ChapterDetailSpiderFactory;
import com.suny.spider.Factory.ChapterSpiderFactory;
import com.suny.spider.entites.Chapter;
import com.suny.spider.entites.ChapterDetail;
import com.suny.spider.interfaces.IChapterDetailSpider;
import com.suny.spider.interfaces.IChapterSpider;
import com.suny.spider.utils.NovelSpiderUtil;
import com.suny.web.entitys.JSONResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Comments:  小说web站点
 * Author:   孙建荣
 * Create Date: 2017/02/27 20:09
 */
@Controller
public class NovelController {

    static {
        NovelSpiderUtil.setConfigPath("conf/Spider-Rule.xml");
    }

    @RequestMapping(value = "/chapters.do" ,method = RequestMethod.GET)
    @ResponseBody
    public JSONResponse getChapter(String url) {
        IChapterSpider chapterSpider = ChapterSpiderFactory.getChapterSpider(url);
        List<Chapter> chapterList = chapterSpider.getChapter(url);
        return JSONResponse.success(chapterList.get(1));
    }

    @RequestMapping(value = "/chapterDetail.do",method = RequestMethod.GET)
    @ResponseBody
    public JSONResponse getChapterDetail(String url) {
        IChapterDetailSpider chapterDetailSpider = ChapterDetailSpiderFactory.getChapterDetailSpider(url);
        ChapterDetail chapterDetail = chapterDetailSpider.getChapterDetail(url);
        return JSONResponse.success(chapterDetail);
    }

    @RequestMapping(value = "test.do")
    @ResponseBody
    public  String test() {
        return "sdf";
    }
}

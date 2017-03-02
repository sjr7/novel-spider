package com.suny.web.controller;

import com.suny.spider.Factory.ChapterDetailSpiderFactory;
import com.suny.spider.Factory.ChapterSpiderFactory;
import com.suny.spider.entites.Chapter;
import com.suny.spider.entites.ChapterDetail;
import com.suny.spider.interfaces.IChapterDetailSpider;
import com.suny.spider.interfaces.IChapterSpider;
import com.suny.spider.utils.NovelSpiderUtil;
import com.suny.web.entitys.JSONResponse;
import com.suny.web.interfaces.INovelService;
import com.suny.web.mapper.NovelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Comments:  小说web站点
 * Author:   孙建荣
 * Create Date: 2017/02/27 20:09
 */
@Controller
public class NovelController {

    @Resource
    private INovelService novelService;

    static {
        NovelSpiderUtil.setConfigPath("conf/Spider-Rule.xml");
    }

    @RequestMapping(value = "/showNovelDetail", method = RequestMethod.GET)
    public ModelAndView showNovelDetail(String url, String baseUrl) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/NovelDetail");
        ChapterDetail chapterDetail = null;
        try {
            chapterDetail = ChapterDetailSpiderFactory.getChapterDetailSpider(url).getChapterDetail(url);
            modelAndView.getModel().put("chapterDetail", chapterDetail);
            modelAndView.getModel().put("isSuccess", true);
            chapterDetail.setContent(chapterDetail.getContent().replaceAll("\n","<br>"));
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.getModel().put("isSuccess", false);
        }
        modelAndView.getModel().put("baseUrl", baseUrl);
        return modelAndView;


    }


    @RequestMapping(value = "/showChapterList", method = RequestMethod.GET)
    public ModelAndView showChapterList(String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/ChapterList");
        modelAndView.getModel().put("chapterList", ChapterSpiderFactory.getChapterSpider(url).getChapter(url));
        modelAndView.getModel().put("baseUrl", url);
        return modelAndView;
    }


    /**
     * 通过关键字跟平台id进行查询
     *
     * @param keyword
     * @param platformId
     * @return
     */
    @RequestMapping(value = "/getNovelByKeywordAndPlatformId", method = RequestMethod.POST)
    @ResponseBody
    public JSONResponse getNovelByKeyWordAndPlatformId(@RequestParam("keyword") String keyword,
                                                       @RequestParam("platformId") Integer platformId) {

        if (keyword == null || keyword.equals("")) {
            return JSONResponse.error("你没有关键词");
        } else if (platformId == null || platformId.equals("")) {
            return JSONResponse.success(novelService.getsNovelByKeyword(keyword));
        } else {
            return JSONResponse.success(novelService.getsNovelByKeyword(keyword, platformId));
        }


    }


    /**
     * 通过关键词搜索
     *
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/getsNovelByKeyword", method = RequestMethod.POST)
    @ResponseBody
    public JSONResponse getsNovelByKeyword(@RequestParam("keyword") String keyword) {
        System.out.println(keyword);
        return JSONResponse.success(novelService.getsNovelByKeyword(keyword));
    }


    @RequestMapping(value = "/chapters", method = RequestMethod.GET)
    @ResponseBody
    public JSONResponse getChapter(String url) {
        IChapterSpider chapterSpider = ChapterSpiderFactory.getChapterSpider(url);
        List<Chapter> chapterList = chapterSpider.getChapter(url);
        return JSONResponse.success(chapterList.get(1));
    }

    @RequestMapping(value = "/chapterDetail", method = RequestMethod.GET)
    @ResponseBody
    public JSONResponse getChapterDetail(String url) {
        IChapterDetailSpider chapterDetailSpider = ChapterDetailSpiderFactory.getChapterDetailSpider(url);
        ChapterDetail chapterDetail = chapterDetailSpider.getChapterDetail(url);
        return JSONResponse.success(chapterDetail);
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "/index";
    }
}

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


    /**
     * 请求对应的一章的数据
     *
     * @param url     章节的url地址
     * @param baseUrl 域名的前缀
     * @return 返回的页面地址跟数据
     */
    @RequestMapping(value = "/showNovelDetail", method = RequestMethod.GET)
    public ModelAndView showNovelDetail(String url, String baseUrl) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/NovelDetail");
        ChapterDetail chapterDetail;
        try {
            chapterDetail = ChapterDetailSpiderFactory.getChapterDetailSpider(url).getChapterDetail(url);
            modelAndView.getModel().put("chapterDetail", chapterDetail);
            modelAndView.getModel().put("isSuccess", true);
            //chapterDetail.setContent(chapterDetail.getContent().replaceAll("\n", "<br>"));
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.getModel().put("isSuccess", false);
        }
        modelAndView.getModel().put("baseUrl", baseUrl);
        return modelAndView;


    }

    /**
     * 请求小说的章节列表
     *
     * @param url 小说的url地址
     * @return 章节json数据
     */
    @RequestMapping(value = "/showChapterList", method = RequestMethod.GET)
    public ModelAndView showChapterList(String url,String novelName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/ChapterList");
        modelAndView.getModel().put("novelName",novelName);
        modelAndView.getModel().put("chapterList", ChapterSpiderFactory.getChapterSpider(url).getChapter(url));
        modelAndView.getModel().put("baseUrl", url);
        return modelAndView;
    }


    /**
     * 通过关键字跟平台id进行查询
     *
     * @param keyword    小说的关键字
     * @param platformId 小说网站对应的id
     * @return 关键字跟平台id联合查询的json数据
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
     * @param keyword 查询的小说关键词
     * @return 得到的小说列表json数据
     */
    @RequestMapping(value = "/getsNovelByKeyword", method = RequestMethod.POST)
    @ResponseBody
    public JSONResponse getsNovelByKeyword(@RequestParam("keyword") String keyword) {
        System.out.println(keyword);
        return JSONResponse.success(novelService.getsNovelByKeyword(keyword));
    }

    /**
     * 请求一本小说
     *
     * @param url 请求的小说的地址
     * @return 小说的json数据
     */
    @RequestMapping(value = "/chapters", method = RequestMethod.GET)
    @ResponseBody
    public JSONResponse getChapter(String url) {
        IChapterSpider chapterSpider = ChapterSpiderFactory.getChapterSpider(url);
        List<Chapter> chapterList = chapterSpider.getChapter(url);
        return JSONResponse.success(chapterList.get(1));
    }


    /**
     * 得到一章小说的文字
     *
     * @param url 章节的url
     * @return 一章小说的json数据
     */
    @RequestMapping(value = "/chapterDetail", method = RequestMethod.GET)
    @ResponseBody
    public JSONResponse getChapterDetail(String url) {
        IChapterDetailSpider chapterDetailSpider = ChapterDetailSpiderFactory.getChapterDetailSpider(url);
        ChapterDetail chapterDetail = chapterDetailSpider.getChapterDetail(url);
        return JSONResponse.success(chapterDetail);
    }


    /**
     * 小说搜索的主页面
     *
     * @return 搜索页面
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "/index";
    }
}

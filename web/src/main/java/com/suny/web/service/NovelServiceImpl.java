package com.suny.web.service;

import com.suny.spider.entites.Novel;
import com.suny.web.interfaces.INovelService;
import com.suny.web.mapper.NovelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Comments:   小说抓取逻辑类
 * Author:   孙建荣
 * Create Date: 2017/02/28 16:21
 */
@Service
public class NovelServiceImpl implements INovelService {

    @Autowired
    private NovelMapper novelMapper;


    /**
     *  自动提示
     * @param keyword  要搜索的关键词
     * @return
     */
    @Override
    public List<Novel> getAutoCompletion(String keyword) {
        keyword = "%" + keyword + "%";
        return novelMapper.getAutoCompletion(keyword);
    }

    /**
     * 通过查询关键字得到小说列表
     *
     * @param keyword 查询的关键字
     * @return 带关键字的小说列表
     */
    @Override
    public List<Novel> getsNovelByKeyword(String keyword) {
        keyword = "%" + keyword + "%";
        return novelMapper.getsNovelByKeyword(keyword);
    }


    /**
     * 通过平台id跟小说的关键字进行查询
     *
     * @param keyword    指定的关键字
     * @param platformId 平台id
     * @return 带两个天剑的小说列表
     */
    @Override
    public List<Novel> getsNovelByKeyword(String keyword, int platformId) {
        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("keyword", "%" + keyword + "%");
        stringStringMap.put("platformId", platformId + "");
        return novelMapper.getsNovelByKeyword2(stringStringMap);
    }
}







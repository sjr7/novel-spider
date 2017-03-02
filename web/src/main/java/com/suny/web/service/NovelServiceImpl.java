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
 * Comments:
 * Author:   孙建荣
 * Create Date: 2017/02/28 16:21
 */
@Service
public class NovelServiceImpl implements INovelService {

    @Autowired
    private NovelMapper novelMapper;


    @Override
    public List<Novel> getsNovelByKeyword(String keyword) {
        keyword = "%" + keyword + "%";
        return novelMapper.getsNovelByKeyword(keyword);
    }

    @Override
    public List<Novel> getsNovelByKeyword(String keyword, int platformId) {
        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("keyword", "%" + keyword + "%");
        stringStringMap.put("platformId", platformId + "");
        return novelMapper.getsNovelByKeyword2(stringStringMap);
    }
}







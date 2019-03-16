package com.suny.spider.novel.storage.mapper;

import com.suny.spider.novel.core.model.Novel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author sunjianrong
 */
@Repository
public interface NovelMapper {

    /**
     * @param keyword 关键字
     * @return 模糊匹配到的数据
     */
    List<Novel> getAutoCompletion(String keyword);

    List<Novel> getsNovelByKeyword2(Map<String, String> stringStringMap);

    List<Novel> getsNovelByKeyword(String keyword);

    int insert(Novel novel);

    int insertSelective(Novel novel);

    void batchInsert(List<Novel> novels);


    int updateByPrimaryKeySelective(Novel record);

    int updateByPrimaryKey(Novel novel);
}
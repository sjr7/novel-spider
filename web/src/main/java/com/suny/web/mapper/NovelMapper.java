package com.suny.web.mapper;

import com.suny.spider.entites.Novel;
import com.suny.spider.entites.NovelExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface NovelMapper {

    List<Novel> getsNovelByKeyword2(Map<String,String> stringStringMap);

    List<Novel> getsNovelByKeyword(String keyword);

    int countByExample(NovelExample example);

    int deleteByExample(NovelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Novel record);

    int insertSelective(Novel record);

    public void batchInsert(List<Novel> novels);

    List<Novel> selectByExample(NovelExample example);

    Novel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Novel record, @Param("example") NovelExample example);

    int updateByExample(@Param("record") Novel record, @Param("example") NovelExample example);

    int updateByPrimaryKeySelective(Novel record);

    int updateByPrimaryKey(Novel record);
}
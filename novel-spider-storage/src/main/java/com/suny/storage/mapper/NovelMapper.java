package com.suny.storage.mapper;

import com.suny.spider.entites.Novel;
import com.suny.spider.entites.NovelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NovelMapper {
    int countByExample(NovelExample example);

    int deleteByExample(NovelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Novel record);

    int insertSelective(Novel record);

    List<Novel> selectByExample(NovelExample example);

    Novel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Novel record, @Param("example") NovelExample example);

    int updateByExample(@Param("record") Novel record, @Param("example") NovelExample example);

    int updateByPrimaryKeySelective(Novel record);

    int updateByPrimaryKey(Novel record);
}
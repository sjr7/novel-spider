package com.suny.spider.novel.storage.mapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class NovelMapperTest {

    @Test
    public void test1() throws FileNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("SqlMapConfig.xml");
        SqlSession sqlSessionFactory = new SqlSessionFactoryBuilder().build(
                inputStream).openSession();
        System.out.println(sqlSessionFactory);
    }

}
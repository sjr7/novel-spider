package com.suny.spider.novel.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 小说爬虫 web 管理后台启动入口.
 *
 * @author sunjianrong
 * @date 2019-3-16 下午4:42
 */
@MapperScan(value = {"com.suny.spider.novel.**.mapper"})
@SpringBootApplication
public class NovelSpiderWebStarter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(NovelSpiderWebStarter.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NovelSpiderWebStarter.class);
    }
}

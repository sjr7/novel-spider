package com.suny.spider.novel.storage.impl;

import com.suny.spider.novel.core.factory.NovelSpiderFactory;
import com.suny.spider.novel.core.interfaces.INovelSpider;
import com.suny.spider.novel.core.model.Novel;
import com.suny.spider.novel.storage.interfaces.Processor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 抽象的小说持久化类
 *
 * @author sunjianrong
 * @date 2017/02/26 15:58
 */
public abstract class AbstractNovelStorage implements Processor {

    Map<String, String> tasks = new TreeMap<>();
    private SqlSessionFactory sqlSessionFactory;

    AbstractNovelStorage() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }


    @Override
    public void process() {
        ExecutorService service = Executors.newFixedThreadPool(tasks.size());
        List<Future<String>> futures = new ArrayList<>(tasks.size());
        for (Map.Entry<String, String> entry : tasks.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            futures.add(service.submit(() -> {
                INovelSpider spider = NovelSpiderFactory.getNovelSpider(value);
                Iterator<List<Novel>> iterator = spider.iterator(value, 10);
                while (iterator.hasNext()) {
                    System.err.println("Start trying to crawl  [" + spider.next() + " ] by [" + key + "]");
                    List<Novel> novels = iterator.next();
                    for (Novel novel : novels) {
                        // 设置小说的名字的首字母
                        novel.setFirstLetter(String.valueOf(key.charAt(0)));
                    }
                    SqlSession sqlSession = sqlSessionFactory.openSession();
                    sqlSession.insert("batchInsert", novels);
                    sqlSession.commit();
                    sqlSession.close();
                    Thread.sleep(1_000);

                }
                return key;
            }));
        }
        service.shutdown();
        for (Future<String> future : futures) {
            try {
                System.out.println(future.get() + " end ");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


}












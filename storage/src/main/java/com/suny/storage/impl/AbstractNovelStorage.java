package com.suny.storage.impl;

import com.suny.spider.Factory.NovelSpiderFactory;
import com.suny.spider.entites.Novel;
import com.suny.spider.interfaces.INovelSpider;
import com.suny.storage.interfaces.Processor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.*;

/**
 * Comments:    抽象的小说持久化类
 * Author:   孙建荣
 * Create Date: 2017/02/26 15:58
 */
public abstract  class AbstractNovelStorage implements Processor{

    protected SqlSessionFactory sqlSessionFactory;

    protected Map<String, String> tasks = new TreeMap<>();

    protected  AbstractNovelStorage() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }


    public void process() {
        ExecutorService service = Executors.newFixedThreadPool(tasks.size());
        List<Future<String>> futures = new ArrayList<>(tasks.size());
        for (Map.Entry<String, String> entry : tasks.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            futures.add(service.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    INovelSpider spider = NovelSpiderFactory.getNovelSpider(value);
                    Iterator<List<Novel>> iterator = spider.iterator(value, 10);
                    while (iterator.hasNext()) {
                        System.err.println("Start trying to crawl  [" + spider.next() + " ] by [" + key+"]");
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
                }
            }));
        }
        service.shutdown();
        for (Future<String> future : futures) {
            try {
                System.out.println(future.get() + " end ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }




}












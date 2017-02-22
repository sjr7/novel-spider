package com.suny.impl;

import com.suny.Factory.ChapterDetailSpiderFactory;
import com.suny.Factory.ChapterSpiderFactory;
import com.suny.configuration.Configuration;
import com.suny.entites.Chapter;
import com.suny.entites.ChapterDetail;
import com.suny.interfaces.IChapterDetailSpider;
import com.suny.interfaces.IChapterSpider;
import com.suny.interfaces.INovelDownload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;

/**
 * 多线程下载小说
 * 孙建荣
 * 2017/02/21 22:03
 */
public class NovelDownload implements INovelDownload {
    @Override
    public String download(String url, Configuration configuration) {
        IChapterSpider spider = ChapterSpiderFactory.getChapterSpider(url);
        List<Chapter> chapterList = spider.getChapter(url);

        //某个线程下载完毕后，你要告诉主线程我下载好了
        int size = configuration.getSize();

        int maxThreadSize = (int) Math.ceil(chapterList.size() * 1.0 / size);
        Map<String, List<Chapter>> downloadTaskAlloc = new HashMap<>();

        for (int i = 0; i < maxThreadSize; i++) {

            int formIndex = i * (configuration.getSize());
            if (i == maxThreadSize - 1) {
                int totalIndex = chapterList.size() - 1;

            }

            int toIndex = i == maxThreadSize - 1 ? chapterList.size() - 1 : i * (configuration.getSize()) + configuration.getSize() - 1;
            downloadTaskAlloc.put(formIndex + "-" + toIndex, chapterList.subList(formIndex, toIndex));
        }
        ExecutorService service = Executors.newFixedThreadPool(maxThreadSize);
        Set<String> keySet = downloadTaskAlloc.keySet();
        List<Future<String>> tasks = new ArrayList<>();
        for (String s : keySet) {

            tasks.add(service.submit(new DownloadCallable(configuration.getPath() + "/" + s + ".txt", downloadTaskAlloc.get(s))));

        }
        service.shutdown();
        for (Future<String> future : tasks) {
            try {
                System.out.println(future.get() + "success download this novel ");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}


    class DownloadCallable implements Callable<String> {
        private List<Chapter> chapters;
        private String path;
        public DownloadCallable(String path, List<Chapter> chapters) {
            this.path = path;
            this.chapters = chapters;
        }
        @Override
        public String call() throws Exception {
            try (
                    PrintWriter out = new PrintWriter(new File(path));
            ) {
                for (Chapter chapter : chapters) {
                    IChapterDetailSpider spider = ChapterDetailSpiderFactory.getChapterDetailSpider(chapter.getUrl());
                    ChapterDetail detail = spider.getChapterDetail(chapter.getUrl());
                    out.println(detail.getTitle());
                    out.println(detail.getContent());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return path;
        }
}















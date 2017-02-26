package com.suny.spider.impl.download;

import com.suny.spider.Factory.ChapterDetailSpiderFactory;
import com.suny.spider.Factory.ChapterSpiderFactory;
import com.suny.spider.configuration.Configuration;
import com.suny.spider.entites.Chapter;
import com.suny.spider.entites.ChapterDetail;
import com.suny.spider.enums.NovelSiteEnum;
import com.suny.spider.interfaces.IChapterDetailSpider;
import com.suny.spider.interfaces.IChapterSpider;
import com.suny.spider.interfaces.INovelDownload;
import com.suny.spider.utils.NovelSpiderUtil;

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
            int toIndex = i == maxThreadSize - 1 ? chapterList.size() : i * (configuration.getSize()) + configuration.getSize();
            downloadTaskAlloc.put(formIndex + "-" + toIndex, chapterList.subList(formIndex, toIndex));
        }
        ExecutorService service = Executors.newFixedThreadPool(maxThreadSize);
        Set<String> keySet = downloadTaskAlloc.keySet();
        List<Future<String>> tasks = new ArrayList<>();

        //解决路径缺失的问题
        String savePath = configuration.getPath() + "/" + NovelSiteEnum.getEnumByUrl(url).getUrl();
        new File(savePath).mkdirs();

        for (String s : keySet) {
            tasks.add(service.submit(new DownloadCallable(savePath + "/" + s + ".txt", downloadTaskAlloc.get(s), configuration.getTryTimes())));
        }
        service.shutdown();
        for (Future<String> future : tasks) {
            try {
                System.out.println(future.get() + " >>>>   success download this novel ");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        NovelSpiderUtil.multiFileMerge(savePath, null, true);
        return savePath + "/merge.txt";
    }
}


class DownloadCallable implements Callable<String> {
    private List<Chapter> chapters;
    private String path;
    private int tryTimes;

    public DownloadCallable(String path, List<Chapter> chapters, int tryTimes) {
        this.path = path;
        this.chapters = chapters;
        this.tryTimes = tryTimes;
    }

    @Override
    public String call() throws Exception {
        try (
                PrintWriter out = new PrintWriter(new File(path));
        ) {
            for (Chapter chapter : chapters) {
                IChapterDetailSpider spider = ChapterDetailSpiderFactory.getChapterDetailSpider(chapter.getUrl());
                ChapterDetail detail = null;
                for (int i = 0; i < tryTimes; i++) {
                    try {
                        detail = spider.getChapterDetail(chapter.getUrl());
                        out.println(detail.getTitle());
                        out.println(detail.getContent());
                        break;
                    } catch (RuntimeException e) {
                        System.err.print("trying fails " + (i + 1) + " download this file");
                    }
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }
}















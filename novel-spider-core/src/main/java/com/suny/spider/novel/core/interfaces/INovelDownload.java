package com.suny.spider.novel.core.interfaces;

import com.suny.spider.novel.core.config.Configuration;

/**
 * 下载小说的接口
 *
 * @author sunjianrong
 * @date 2017/02/21 21:49
 */
public interface INovelDownload {

    /**
     * 下载小说的方法
     *
     * @param url           下载小说的地址
     * @param configuration 下载小说的配置文件
     * @return 下载好的小说地址
     */
    String download(String url, Configuration configuration);
}

package com.suny.spider.interfaces;

import com.suny.spider.configuration.Configuration;

/**
 * Comments:    下载小说的接口
 * Author:      孙建荣
 * Create Date: 2017/02/21 21:49
 */
public interface INovelDownload {

    /**
     * 下载小说的方法
     * @param url   下载小说的地址
     * @param configuration   下载小说的配置文件
     * @return
     */
    public String download(String url,Configuration configuration);
}

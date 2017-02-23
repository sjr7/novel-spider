package com.suny.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

import java.net.URI;

/**
 * Comments:  自定义实现的httpGet类，用于自定义连接网络时的配置参数
 * Author:   孙建荣
 * Create Date: 2017/02/23 13:10
 */
public class NovelSpiderHttpGet extends HttpGet {

    public NovelSpiderHttpGet() {
    }

    public NovelSpiderHttpGet(URI uri) {
        super(uri);
    }

    public NovelSpiderHttpGet(String uri) {
        super(uri);
    }


    /**
     * 设置默认的请求参数，我们自己自定义我们需要的
     */
    private void setDefaultConfig() {
        this.setConfig(RequestConfig.custom()
                .setSocketTimeout(2_000)
                .setConnectTimeout(10_000)    // 是设置连接服务器的超时时间
                .setConnectionRequestTimeout(10_000)   //  是设置从服务器读取数据的超时时间
                .build());
        this.setHeader("User-Agent", "NovelSpider");   //  设置请求头
    }


}




















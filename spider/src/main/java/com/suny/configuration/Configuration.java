package com.suny.configuration;

import java.io.Serializable;

/**
 * Comments:   程序下载小说的配置文件
 * Author:      孙建荣
 * Create Date:   2017/02/21 21:51
 */
public class Configuration implements Serializable {

    /**
     * 下载每一章节的失败后最大尝试的次数
     */
    public static final int DEFAULT_TRY_TIMES = 3;

    /**
     * 每个线程默认下载的最大章节数
     */
    public static final int DEFAULT_SIZE = 100;

    /**
     * 下载后文件的保存位置
     */
    private String path;


    /**
     * 每个线程能够下载的最大章节数
     */
    private int size;

    /**
     * 下载失败后尝试的次数
     */
    private int tryTimes;

    public Configuration() {
        // 把默认配置的参数传进去
        this.size = DEFAULT_SIZE;
        this.tryTimes = DEFAULT_TRY_TIMES;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTryTimes() {
        return tryTimes;
    }

    public void setTryTimes(int tryTimes) {
        this.tryTimes = tryTimes;
    }
}

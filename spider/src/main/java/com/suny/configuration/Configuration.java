package com.suny.configuration;

import java.io.Serializable;

/**
 * 下载小说的配置文件
 * 孙建荣
 * 2017/02/21 21:51
 */
public class Configuration implements Serializable {

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

    public Configuration (){
        this.size = DEFAULT_SIZE;
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
}

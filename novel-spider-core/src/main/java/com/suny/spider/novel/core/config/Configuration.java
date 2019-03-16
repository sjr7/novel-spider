package com.suny.spider.novel.core.config;

import lombok.*;

import java.io.Serializable;

/**
 * 程序下载小说的配置文件
 *
 * @author sunjianrong
 * @date 2017/02/21 21:51
 */
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Configuration implements Serializable {

    private static final long serialVersionUID = 2729255133861275288L;

    /**
     * 下载每一章节的失败后最大尝试的次数
     */
    private static final int DEFAULT_TRY_TIMES = 3;


    /**
     * 每个线程默认下载的最大章节数
     */
    private static final int DEFAULT_SIZE = 100;

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

}

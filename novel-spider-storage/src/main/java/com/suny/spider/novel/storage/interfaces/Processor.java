package com.suny.spider.novel.storage.interfaces;

/**
 * 自定义一个处理器接口，多线程相关的类
 *
 * @author sunjianrong
 * @date 2017/02/26 15:56
 */
public interface Processor {

    /**
     * 一个处理方法
     */
    void process();
}

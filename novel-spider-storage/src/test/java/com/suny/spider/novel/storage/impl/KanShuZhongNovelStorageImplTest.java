package com.suny.spider.novel.storage.impl;

import com.suny.spider.novel.storage.interfaces.Processor;
import org.junit.Test;

public class KanShuZhongNovelStorageImplTest {

    @Test
    public void test() {
        Processor processor = new KanShuZhongNovelStorageImpl();
        processor.process();
    }

}
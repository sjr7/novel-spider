package com.suny.spider.novel.storage.impl;

import com.suny.spider.novel.storage.interfaces.Processor;
import org.junit.Test;

import java.io.FileNotFoundException;

public class BxwxNovelStorageImplTest {

    @Test
    public void testBxwxProcess() throws FileNotFoundException {
        Processor processor = new BxwxNovelStorageImpl();
        processor.process();
    }

}
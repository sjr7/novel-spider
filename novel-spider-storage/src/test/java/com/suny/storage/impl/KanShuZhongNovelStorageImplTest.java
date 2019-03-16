package com.suny.storage.impl;

import com.suny.storage.interfaces.Processor;
import org.junit.Test;

import static org.junit.Assert.*;

public class KanShuZhongNovelStorageImplTest {

    @Test
    public void test() {
        Processor processor = new KanShuZhongNovelStorageImpl();
        processor.process();
    }
    
}
package com.suny.storage.impl;

import com.suny.storage.interfaces.Processor;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class BxwxNovelStorageImplTest {

    @Test
    public void testBxwxProcess() throws FileNotFoundException {
        Processor processor = new BxwxNovelStorageImpl();
        processor.process();
    }
    
}
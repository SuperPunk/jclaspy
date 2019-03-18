package com.wajyl.elements;

import com.wajyl.ClazzParser;
import com.wajyl.utils.DataTypeConverter;

import java.io.IOException;
import java.io.InputStream;

public class SuperClassRef extends ClazzParser {
    private static final int NUM_BYTES = 2;
    private byte[] bytes = new byte[NUM_BYTES];

    public void interpret() {
        System.out.println(DataTypeConverter.bytesToHex(bytes)+"\t#父类索引");
    }

    public void build(InputStream input) throws IOException {
        input.read(bytes, 0, NUM_BYTES);
    }
}

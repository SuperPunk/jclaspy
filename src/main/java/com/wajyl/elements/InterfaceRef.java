package com.wajyl.elements;

import com.wajyl.ClazzParser;
import com.wajyl.utils.DataTypeConverter;

import java.io.IOException;
import java.io.InputStream;

public class InterfaceRef extends ClazzParser {
    private static final short NUM_BYTES_OF_INTERFACE_COUNT = 2;
    private byte[] countBytes = new byte[NUM_BYTES_OF_INTERFACE_COUNT];
    private byte[] refBytes;

    public void interpret() {
        String countString = DataTypeConverter.bytesToHex(countBytes);
        String refString = DataTypeConverter.bytesToHex(refBytes);

        System.out.println(countString + " " + refString + "\t#接口索引(计数 索引值列表)");
    }

    public void build(InputStream input) throws IOException {
        input.read(countBytes, 0, NUM_BYTES_OF_INTERFACE_COUNT);
        int numOfRefs = DataTypeConverter.bytesToShort(countBytes);
        int shoudReadBytes = numOfRefs * 2;
        refBytes = new byte[shoudReadBytes];
        input.read(refBytes, 0, shoudReadBytes);
    }
}

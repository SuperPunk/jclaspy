package com.wajyl.elements.cp;

import com.wajyl.elements.ConstantPool;
import com.wajyl.utils.DataTypeConverter;

import java.io.IOException;
import java.io.InputStream;

public class CPDouble extends ConstantPool {
    private static final int NUM_BYTES = 9;
    private byte[] bytes = new byte[NUM_BYTES];

    @Override
    public void interpret() {
        String hexString = DataTypeConverter.bytesToHex(bytes);
        //tag length UTF8String
        System.out.println(hexString.substring(0, 2) + " " + hexString.substring(2));
    }

    @Override
    public void build(InputStream input) throws IOException {
        input.read(bytes, 0, NUM_BYTES);
    }
}

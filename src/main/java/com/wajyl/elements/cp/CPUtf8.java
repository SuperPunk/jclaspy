package com.wajyl.elements.cp;

import com.wajyl.elements.ConstantPool;
import com.wajyl.utils.DataTypeConverter;

import java.io.IOException;
import java.io.InputStream;

public class CPUtf8 extends ConstantPool {
    private byte[] bytes;

    @Override
    public void interpret() {
        String hexString = DataTypeConverter.bytesToHex(bytes);
        //tag length UTF8String
        System.out.println(
                hexString.substring(0, 2) + " " + hexString.substring(2, 6) + " " + hexString.substring(6)
        );
    }

    @Override
    public void build(InputStream input) throws IOException {
        byte[] tag = new byte[1];
        byte[] length = new byte[2];
        input.read(tag, 0, 1);
        input.read(length, 0, 2);
        int lengthOfUtf8String = DataTypeConverter.bytesToShort(length);
        bytes = new byte[1 + 2 + lengthOfUtf8String];
        bytes[0] = tag[0];
        bytes[1] = length[0];
        bytes[2] = length[1];
        input.read(bytes, 3, lengthOfUtf8String);
    }
}

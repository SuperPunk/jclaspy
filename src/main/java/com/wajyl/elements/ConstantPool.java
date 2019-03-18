package com.wajyl.elements;

import com.wajyl.ClazzParser;
import com.wajyl.elements.cp.CPDouble;
import com.wajyl.elements.cp.CPLong;
import com.wajyl.elements.cp.CPType;
import com.wajyl.utils.DataTypeConverter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ConstantPool extends ClazzParser {
    private static final int NUM_BYTES_OF_CONSTANT_POOL_COUNT = 2;
    private byte[] cpCountBytes = new byte[NUM_BYTES_OF_CONSTANT_POOL_COUNT];
    private int cpCount;    //常量项数
    private List<ConstantPool> cpItems = new ArrayList<>();

    @Override
    public void interpret() {
        System.out.println(DataTypeConverter.bytesToHex(cpCountBytes) + "\t# 常量池项数+1");
        int count = 1;
        for (ConstantPool cpItem : cpItems) {
            System.out.print("[" + (count++) + "] ");
            cpItem.interpret();
            //double或者long类型占两项常量
            if (cpItem instanceof CPLong || cpItem instanceof CPDouble) {
                System.out.println("[" + (count++) + "] large numeric continued...");
            }
        }
    }

    @Override
    public void build(InputStream input) throws IOException {
        input.read(cpCountBytes, 0, NUM_BYTES_OF_CONSTANT_POOL_COUNT);
        cpCount = DataTypeConverter.bytesToShort(cpCountBytes) - 1;   //常量项从1开始计数
        for (int i = 1; i <= cpCount; i++) {
            Class<? extends ConstantPool> cpTypeClazz = CPType.getClazz(foreseeCpTag(input));
            try {
                ConstantPool cp = cpTypeClazz.newInstance();
                cp.build(input);
                cpItems.add(cp);
                //double或者long类型占两项常量
                if (cp instanceof CPLong || cp instanceof CPDouble) {
                    i++;
                }
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    private int foreseeCpTag(InputStream input) throws IOException {
        input.mark(1);
        int tag = input.read();
        input.reset();
        return tag;
    }
}

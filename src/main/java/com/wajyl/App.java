package com.wajyl;

import com.wajyl.elements.AccessFlag;
import com.wajyl.elements.ConstantPool;
import com.wajyl.elements.MagicNumber;
import com.wajyl.elements.Version;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        InputStream inputStream = new BufferedInputStream(new FileInputStream(filename));
        List<ClazzParser> elements = Arrays.asList(
                new MagicNumber(),
                new Version(),
                new ConstantPool(),
                new AccessFlag()
        );
        for (ClazzParser element : elements) {
            element.build(inputStream);
            element.interpret();
        }
    }
}

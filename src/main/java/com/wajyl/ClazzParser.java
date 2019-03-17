package com.wajyl;

import java.io.IOException;
import java.io.InputStream;

public abstract class ClazzParser {
    public abstract void interpret();

    public abstract void build(InputStream input) throws IOException;
}

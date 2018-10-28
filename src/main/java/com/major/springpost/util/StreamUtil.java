package com.major.springpost.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtil {

    public static void readStream(InputStream is, OutputStream os) throws IOException {
        byte[] buff = new byte[1024];
        int len;
        while ((len = is.read(buff)) != -1) {
            os.write(buff, 0, len);
        }
        is.close();
        os.close();
    }
}

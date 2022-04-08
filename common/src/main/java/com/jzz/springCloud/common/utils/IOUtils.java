package com.jzz.springCloud.common.utils;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {

    /**
     * 关闭对象，连接
     *
     * @param closeable 可关闭的流对象
     */
    public static void closeQuietly(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException ioe) {
            // ignore
        }
    }
}

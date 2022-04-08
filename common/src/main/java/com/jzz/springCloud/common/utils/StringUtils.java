package com.jzz.springCloud.common.utils;

public class StringUtils {

    /**
     * 判空操作
     *
     * @param value 要判断的字符串
     * @return true或false
     */
    public static boolean isBlank(String value) {
        return value == null || "".equals(value) || "null".equals(value) || "undefined".equals(value);
    }

}

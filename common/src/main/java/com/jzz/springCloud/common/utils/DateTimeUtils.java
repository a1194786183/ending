package com.jzz.springCloud.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前标准格式化日期时间
     *
     * @return 当前时间的字符串
     */
    public static String getDateTime() {
        return getDateTime(new Date());
    }

    /**
     * 标准格式化日期时间
     *
     * @param date 要格式化的日期对象
     * @return 格式化后的日期
     */
    public static String getDateTime(Date date) {
        return (new SimpleDateFormat(DATE_FORMAT)).format(date);
    }
}

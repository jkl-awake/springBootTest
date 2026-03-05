package com.example.demo.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期处理工具类
 */
public final class DateUtils {
    // 1. 将 Formatter 定义为常量，避免重复解析 pattern，提升性能
    // DateTimeFormatter 是线程安全的，可以放心作为静态变量
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    // 2. 私有构造函数，防止工具类被实例化
    private DateUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    /**
     * 将 LocalDate 转换为 yyyy-MM-dd 00:00:00 格式的字符串
     * @param date 日期
     * @return 格式化后的字符串
     */
    public static String formatLocalDateWithStartTime(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return date.format(DATE_TIME_FORMATTER);
    }
    /**
     * 重载方法：支持自定义格式（可选）
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) return null;
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}

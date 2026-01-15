package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonUtils {
// 1. 定义一个静态的 ObjectMapper，用来给静态方法用
    private static ObjectMapper mapper;

    // 2. 关键点：这是一个非静态的 Set 方法
    // Spring 容器启动时，会自动把配置好的 ObjectMapper 注入进来
    // 然后我们手动赋值给上面的静态变量 mapper
    @Autowired
    private void setMapper(ObjectMapper objectMapper) {
        JsonUtils.mapper = objectMapper;
    }

    // 3. 私有构造方法，防止被 new
    private JsonUtils() {}

    /**
     * 安静地将对象转换为 JSON 字符串
     * 专门用于日志打印，失败时返回占位符，不会抛出异常干扰主业务
     */
    public static String toSilentJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            // 发生异常时，不抛出，而是返回对象本身的 toString 字符串或自定义文本
            // 这样可以保证日志一定会打印出来，哪怕 JSON 格式化失败了
            return obj.toString();
        }
    }
    
    // 你还可以扩展其他静态方法，比如 parse
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON Parse Error", e); // 非日志方法通常需要抛出异常
        }
    }
}

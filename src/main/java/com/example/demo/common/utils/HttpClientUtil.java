package com.example.demo.common.utils;

import io.micrometer.common.lang.Nullable;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class HttpClientUtil {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    private static final String DEFAULT_CONTENT_TYPE = "application/json";

    /**
     * GET 请求
     */
    public static String get(String url,String token) {
        if(token.isEmpty()){
            return get(url, new HashMap<>(),null);
        }

        return get(url, new HashMap<>(),token);
    }

    public static String get(String url, Map<String, String> headers,@Nullable String token) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();

        // 添加请求头
        headers.forEach(requestBuilder::header);

        if(token != null && !token.isEmpty()){
            addAuthHeader(requestBuilder,token);
        }

        try {
            HttpResponse<String> response = httpClient.send(
                    requestBuilder.build(),
                    HttpResponse.BodyHandlers.ofString()
            );
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException("GET 请求失败: " + url, e);
        }
    }

    /**
     * POST 请求（表单形式）
     */
    public static String postForm(String url, Map<String, String> formData) {
        StringBuilder body = new StringBuilder();
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            if (!body.isEmpty()) body.append("&");
            body.append(entry.getKey()).append("=").append(entry.getValue());
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException("POST 请求失败: " + url, e);
        }
    }

    /**
     * POST 请求（JSON 形式）
     */
    public static String postJson(String url, String jsonBody,String token) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody));

        if(!token.isEmpty()){
            addAuthHeader(builder,token);
        }

        try {
            HttpResponse<String> response = httpClient.send(
                    builder.build(),
                    HttpResponse.BodyHandlers.ofString()
            );
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException("POST 请求失败: " + url, e);
        }
    }

    /**
     * POST 请求（JSON 形式，带自定义请求头）
     */
    public static String postJson(String url, String jsonBody, Map<String, String> headers) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody));

        headers.forEach(requestBuilder::header);

        try {
            HttpResponse<String> response = httpClient.send(
                    requestBuilder.build(),
                    HttpResponse.BodyHandlers.ofString()
            );
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException("POST 请求失败: " + url, e);
        }
    }

    /**
     * 添加额外请求头
     */
    private static void addExtraHeaders(HttpRequest.Builder builder, Map<String, String> headers) {
        if (headers != null) {
            headers.forEach(builder::header);
        }
    }

    /**
     * 添加认证 Header
     */
    private static void addAuthHeader(HttpRequest.Builder builder, String token) {
        if (token != null && !token.isEmpty()) {
            String authValue;
            // 判断是否已有 "bearer " 前缀（不改变 token 本身）
            if (token.toLowerCase().startsWith("bearer ")) {
                authValue = token;
            } else {
                authValue = "Bearer " + token;
            }
            builder.header("Authorization", authValue);
        }
    }
}

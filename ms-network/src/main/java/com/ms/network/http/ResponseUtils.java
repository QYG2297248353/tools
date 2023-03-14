/*
 * @MS 2022-12-13
 * Copyright (c) 2001-2023 萌森
 * 保留所有权利
 * 本软件为萌森工作室所有及包含机密信息，须遵守其相关许可证条款进行使用。
 * Copyright (c) 2001-2023 Meng Sen
 * All rights reserved
 * This software is owned by Mengsen Studio and contains confidential information, and must be used in accordance with its relevant license terms.
 * Website：https://qyg2297248353.top
 */

package com.ms.network.http;

import com.alibaba.fastjson2.JSON;
import com.ms.core.response.ResponseResult;
import com.ms.network.http.enums.MediaTypeEnum;
import com.ms.network.http.servlet.CacheHttpServletResponse;
import com.ms.network.http.servlet.HttpServletFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ms2297248353
 */
public class ResponseUtils {

    /**
     * 复制响应
     *
     * @param response 请求
     * @return 以复制的流
     * @throws IOException 读写异常
     */
    public static ServletOutputStream copyStream(HttpServletResponse response) throws IOException {
        CacheHttpServletResponse cacheHttpServletResponse = new CacheHttpServletResponse(response);
        return cacheHttpServletResponse.getOutputStream();
    }

    /**
     * 复制响应
     *
     * @param response 请求
     * @return 以复制的流
     * @throws IOException 读写异常
     */
    public static HttpServletResponse copy(HttpServletResponse response) throws IOException {
        return new CacheHttpServletResponse(response);
    }


    /**
     * 写入响应-纯文本
     *
     * @param response 响应
     * @param content  内容
     */
    public static void writeText(HttpServletResponse response, String content) {
        response.setContentType(MediaTypeEnum.TEXT_PLAIN.getValue());
        HttpServletFactory.write(response, content);
    }

    /**
     * 写入响应-HTML
     *
     * @param response 响应
     * @param content  内容
     */
    public static void writeHtml(HttpServletResponse response, String content) {
        response.setContentType(MediaTypeEnum.TEXT_HTML.getValue());
        HttpServletFactory.write(response, content);
    }

    /**
     * 写入响应-JSON
     *
     * @param response 响应
     * @param content  内容
     */
    public static void writeJson(HttpServletResponse response, String content) {
        response.setContentType(MediaTypeEnum.APPLICATION_JSON.getValue());
        HttpServletFactory.write(response, content);
    }

    /**
     * 写入响应-JSON
     *
     * @param response 响应
     * @param content  内容
     */
    public static void writeJsonObject(HttpServletResponse response, Object content) {
        String json = JSON.toJSONString(content);
        response.setContentType(MediaTypeEnum.APPLICATION_JSON.getValue());
        HttpServletFactory.write(response, json);
    }

    /**
     * 写入响应-JSON
     *
     * @param response 响应
     * @param result   实体内容
     */
    public static void writeJsonObject(HttpServletResponse response, ResponseResult result) {
        String json = JSON.toJSONString(result);
        response.setContentType(MediaTypeEnum.APPLICATION_JSON.getValue());
        HttpServletFactory.write(response, json);
    }


}

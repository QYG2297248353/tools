/*
 * @MS 2022-12-13
 *  Copyright (c) 2001-2023 萌森
 *  保留所有权利
 *  本软件为萌森工作室所有及包含机密信息，须遵守其相关许可证条款进行使用。
 *  Copyright (c) 2001-2023 Meng Sen
 *  All rights reserved
 * This software is owned by Mengsen Studio and contains confidential information, and must be used in accordance with its relevant license terms.
 * Website：https://qyg2297248353.top
 */

package com.ms.network.core;

import com.alibaba.fastjson2.JSON;
import com.ms.network.factory.response.ResponseFactory;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ms2297248353
 */
public class ResponseUtils {
    /**
     * 写入响应-纯文本
     *
     * @param response 响应
     * @param content  内容
     */
    public static void writeText(HttpServletResponse response, String content) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        ResponseFactory.write(response, content);
    }

    /**
     * 写入响应-HTML
     *
     * @param response 响应
     * @param content  内容
     */
    public static void writeHtml(HttpServletResponse response, String content) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.TEXT_HTML_VALUE);
        ResponseFactory.write(response, content);
    }

    /**
     * 写入响应-JSON
     *
     * @param response 响应
     * @param content  内容
     */
    public static void writeJson(HttpServletResponse response, String content) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ResponseFactory.write(response, content);
    }

    /**
     * 写入响应-JSON
     *
     * @param response 响应
     * @param content  内容
     */
    public static void writeJsonObject(HttpServletResponse response, Object content) {
        String json = JSON.toJSONString(content);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ResponseFactory.write(response, json);
    }


}

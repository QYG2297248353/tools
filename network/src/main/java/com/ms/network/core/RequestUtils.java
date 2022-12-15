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


import com.ms.network.factory.request.CachedBodyHttpServletRequest;
import com.ms.network.factory.response.CachedBodyHttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import static com.ms.base.enums.ip.BaseIp.*;

/**
 * request 请求工具
 *
 * @author ms2297248353
 */
public class RequestUtils {
    private static final Logger LOGGER = Logger.getLogger(RequestUtils.class.getName());

    /**
     * 复制请求
     *
     * @param request 请求
     * @return 以复制的流
     * @throws IOException 读写异常
     */
    public static ServletInputStream copyRequest(HttpServletRequest request) throws IOException {
        CachedBodyHttpServletRequest copyRequest = new CachedBodyHttpServletRequest(request);
        return copyRequest.getInputStream();
    }

    /**
     * 复制请求
     *
     * @param request 请求
     * @return 以复制的流
     * @throws IOException 读写异常
     */
    public static CachedBodyHttpServletRequest getCopyRequest(HttpServletRequest request) throws IOException {
        return new CachedBodyHttpServletRequest(request);
    }

    /**
     * 复制响应
     *
     * @param response 请求
     * @return 以复制的流
     * @throws IOException 读写异常
     */
    public static CachedBodyHttpServletResponse getCopyResponse(HttpServletResponse response) throws IOException {
        return new CachedBodyHttpServletResponse(response);
    }

    /**
     * 获取表单中的字段，请求类型是application/x-www-form-urlencoded
     *
     * @param request request
     * @return 返回字段内容
     */
    public static Map<String, String> getFormFields(HttpServletRequest request) {
        String query;
        try {
            ServletInputStream inputStream = copyRequest(request);
            query = IOUtils.toString(inputStream, request.getCharacterEncoding());
        } catch (IOException e) {
            LOGGER.warning("获取form参数失败");
            throw new RuntimeException("请求失败");
        }
        return parseQueryString(query);
    }

    /**
     * 获取url参数
     *
     * @param query 请求连接
     * @return 请求参数
     */
    public static Map<String, String> parseQueryString(String query) {
        if (StringUtils.hasText(query)) {
            return Collections.emptyMap();
        }
        query = StringUtils.trimLeadingCharacter(query, '?');
        String[] pairs = query.split("&");
        Map<String, String> form = new HashMap<>(pairs.length * 2);
        for (String pair : pairs) {
            String[] param = pair.split("=");
            String key = param[0];
            String value = UriUtils.decode(param[1], StandardCharsets.UTF_8);
            form.put(key, value);
        }
        return form;
    }

    /**
     * 获取上传文件表单中的字段，排除query参数
     *
     * @param request request
     * @return 返回字段内容
     */
    public static Map<String, String> getMultipartFields(HttpServletRequest request) {
        Map<String, String> queryParams = parseQueryString(request.getQueryString());
        Map<String, String> uploadParams = new HashMap<>(16);
        Set<String> queryKeys = queryParams.keySet();
        request.getParameterMap().forEach((key, value) -> {
            // 排除query param
            if (!queryKeys.contains(key)) {
                uploadParams.put(key, value[0]);
            }
        });
        return uploadParams;
    }

    /**
     * 获取客户端IP
     *
     * @param request request
     * @return 返回ip
     */
    public static String getIP(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (IP_LOCAL.equals(ipAddress)) {
                // 根据网卡取本机配置的IP
                try {
                    InetAddress inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    // ignore
                }
            }

        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > IP_LEN) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

}

package com.ms.network.http;

import com.ms.core.base.basic.Strings;
import com.ms.core.exception.base.MsToolsRuntimeException;

import java.util.Map;

/**
 * @author ms2297248353
 */
public class UriUtils {

    /**
     * 生成url
     *
     * @param uri    请求地址
     * @param params 请求参数
     * @return url
     */
    public static String buildUrl(String uri, Map<String, Object> params) {
        if (Strings.isBlank(uri)) {
            throw new MsToolsRuntimeException("请求地址不能为空");
        }
        if (params.isEmpty()) {
            return uri;
        }
        if (uri.endsWith(Strings.SLASH)) {
            uri = uri.substring(0, uri.length() - 1);
        }
        StringBuilder sb = new StringBuilder(uri);
        if (uri.contains(Strings.QUESTION_MARK)) {
            sb.append(Strings.AND);
        } else {
            sb.append(Strings.QUESTION_MARK);
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            sb.append(entry.getKey()).append(Strings.EQUALS).append(entry.getValue()).append(Strings.AND);
        }
        sb.deleteCharAt(sb.length() - 1);
        uri = sb.toString();
        return uri;
    }

    /**
     * 特殊字符转义
     * 处理url中的特殊字符
     *
     * @param url
     * @return String
     */
    public static String encodeUrl(String url) {
        if (url == null) {
            return null;
        }
        return url.replaceAll(" ", "%20").replaceAll("\\+", "%2B").replaceAll("\\*", "%2A").replaceAll("\\?", "%3F")
                .replaceAll("\\$", "%24").replaceAll("\\^", "%5E").replaceAll("\\|", "%7C").replaceAll("\\{", "%7B")
                .replaceAll("\\}", "%7D").replaceAll("\\[", "%5B").replaceAll("\\]", "%5D").replaceAll("\\(", "%28")
                .replaceAll("\\)", "%29").replaceAll("\\#", "%23").replaceAll("\\!", "%21").replaceAll("\\'", "%27")
                .replaceAll("\\,", "%2C").replaceAll("\\;", "%3B").replaceAll("\\:", "%3A").replaceAll("\\@", "%40")
                .replaceAll("\\&", "%26").replaceAll("\\=", "%3D").replaceAll("\\/", "%2F").replaceAll("\\\\", "%5C");
    }

    /**
     * 还原转义字符
     * 处理url中的特殊字符
     *
     * @param url
     * @return String
     */
    public static String decodeUrl(String url) {
        if (url == null) {
            return null;
        }
        return url.replaceAll("%20", " ").replaceAll("%2B", "\\+").replaceAll("%2A", "\\*").replaceAll("%3F", "\\?")
                .replaceAll("%24", "\\$").replaceAll("%5E", "\\^").replaceAll("%7C", "\\|").replaceAll("%7B", "\\{")
                .replaceAll("%7D", "\\}").replaceAll("%5B", "\\[").replaceAll("%5D", "\\]").replaceAll("%28", "\\(")
                .replaceAll("%29", "\\)").replaceAll("%23", "\\#").replaceAll("%21", "\\!").replaceAll("%27", "\\'")
                .replaceAll("%2C", "\\,").replaceAll("%3B", "\\;").replaceAll("%3A", "\\:").replaceAll("%40", "\\@")
                .replaceAll("%26", "\\&").replaceAll("%3D", "\\=").replaceAll("%2F", "\\/").replaceAll("%5C", "\\\\");
    }
}

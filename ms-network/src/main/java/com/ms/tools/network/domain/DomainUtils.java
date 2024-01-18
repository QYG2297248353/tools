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

package com.ms.tools.network.domain;

import com.ms.tools.core.base.basic.Lists;
import com.ms.tools.core.base.basic.Strings;
import com.ms.tools.network.ip.IpUtils;
import org.xbill.DNS.*;

import java.net.IDN;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 域名解析工具类
 *
 * @author ms
 */
public class DomainUtils {

    /**
     * 协议
     */
    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";

    /**
     * 域名
     */
    private static final String HOST = "www";

    /**
     * 结尾
     */
    private static final String END = "/";

    /**
     * 点
     */
    private static final String LEVEL = ".";

    private static final String LEVEL_REGULAR = "\\.";

    /**
     * 开始
     */
    private static final String START = "://";

    /**
     * China 顶级域名 常量
     * <p>
     * .cn
     * .gov.cn
     */
    private static final String[] CHINA_TOP_LEVEL_DOMAIN = {
            ".gov.cn",
            ".com.cn",
            ".net.cn",
            ".org.cn",
            ".ac.cn",
            ".zj.cn",
            ".yn.cn",
            ".xz.cn",
            ".xj.cn",
            ".tw.cn",
            ".tj.cn",
            ".sx.cn",
            ".sn.cn",
            ".sh.cn",
            ".sd.cn",
            ".sc.cn",
            ".qh.cn",
            ".nx.cn",
            ".nm.cn",
            ".mo.cn",
            ".ln.cn",
            ".jx.cn",
            ".js.cn",
            ".jl.cn",
            ".hn.cn",
            ".hk.cn",
            ".hl.cn",
            ".hi.cn",
            ".he.cn",
            ".hb.cn",
            ".ha.cn",
            ".gd.cn",
            ".gx.cn",
            ".gz.cn",
            ".gs.cn",
            ".fj.cn",
            ".cq.cn",
            ".bj.cn",
            ".ah.cn"
    };

    /**
     * China 中文顶级域名 常量
     * <p>
     * .中国
     */
    private static final String[] CHINA_CHINESE_TOP_LEVEL_DOMAIN = {
            ".中国",
            ".在线",
            ".手机",
            ".网店",
            ".集团",
            ".我爱你",
            ".企业",
            ".佛山",
            ".网址",
            ".餐厅",
            ".中文网",
            ".移动",
            ".公司",
            ".网络",
            ".网站",
            ".购物",
            ".商店",
            ".信息",
            ".游戏",
            ".娱乐",
            ".招聘",
            ".政务"
    };
    private static final String URL_REGULAR = "^(http|https)://.*";

    /**
     * 中文域名转换ASCII
     *
     * @param domain 根域名
     * @return 转换后的域名
     */
    public static String chineseCoverUrl(String domain) {
        // 判断是否是中文域名
        boolean chineseDomain = isChineseDomain(domain);
        if (!chineseDomain) {
            return domain;
        }
        String protocol = getProtocol(domain);
        domain = domain.replace(protocol, "");
        String urlPath = getUrlPath(domain);
        domain = domain.replace(urlPath, "");
        String ascii = IDN.toASCII(domain);
        if (!protocol.isEmpty()) {
            ascii = protocol + ascii;
        }
        if (!urlPath.isEmpty()) {
            ascii = ascii + urlPath;
        }
        return ascii;
    }

    /**
     * 中文ASCII转换中文域名
     */
    public static String urlCoverChinese(String domain) {
        String protocol = getProtocol(domain);
        domain = domain.replace(protocol, "");
        String urlPath = getUrlPath(domain);
        domain = domain.replace(urlPath, "");
        String unicode = IDN.toUnicode(domain);
        if (!protocol.isEmpty()) {
            unicode = protocol + unicode;
        }
        if (!urlPath.isEmpty()) {
            unicode = unicode + urlPath;
        }
        return unicode;
    }

    /**
     * 判断是否是中文域名
     *
     * @param domain 域名
     * @return 是否是中文域名
     */
    public static boolean isChineseDomain(String domain) {
        String protocol = getProtocol(domain);
        domain = domain.replace(protocol, "");
        int indexOf = domain.indexOf(END);
        if (indexOf != -1) {
            domain = domain.substring(0, indexOf);
        }
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher matcher = pattern.matcher(domain);
        return matcher.find();
    }

    /**
     * 获取顶级域名
     * <p>
     * https://www.baidu.com
     * 转为
     * baidu.com
     *
     * @param domain 域名
     * @return 顶级域名
     */
    public static String getTopLevelDomain(String domain) {
        String host = getHost(domain);
        // ip 域名
        if (IpUtils.isIpPort(host)) {
            return host;
        }
        // 判断是否是中文域名
        boolean chineseDomain = isChineseDomain(host);
        if (chineseDomain) {
            String[] split = host.split(LEVEL_REGULAR);
            // 只保留最后 xxx.xxx 二级
            if (split.length > 2) {
                host = split[split.length - 2] + LEVEL + split[split.length - 1];
            }
            return host;
        }
        // 判断是否是中国顶级域名 com.cn .gov.cn
        boolean chineseTopDomain = isChinaTopDomain(domain);
        if (chineseTopDomain) {
            String[] split = host.split(LEVEL_REGULAR);
            // 只保留最后 xxx.xxx.xxx 三级
            if (split.length > 3) {
                host = split[split.length - 3] + LEVEL + split[split.length - 2] + LEVEL + split[split.length - 1];
            }
            return host;
        }
        // 保留最后 xxx.xxx 二级
        String[] split = host.split(LEVEL_REGULAR);
        if (split.length > 2) {
            host = split[split.length - 2] + LEVEL + split[split.length - 1];
        }
        return host;
    }

    /**
     * 是否为中国顶级域名
     *
     * @param domain 域名
     * @return 是否为中国顶级域名
     * @see #CHINA_TOP_LEVEL_DOMAIN
     */
    public static boolean isChinaTopDomain(String domain) {
        String host = getHost(domain);
        for (String chinaTopLevelDomain : CHINA_TOP_LEVEL_DOMAIN) {
            if (host.endsWith(chinaTopLevelDomain)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取协议
     * <p>
     * http(s)://www.baidu.com/xxx/xxx?s='xxx'
     * 转为
     * http(s)://
     *
     * @param domain 域名
     * @return 协议
     */
    public static String getProtocol(String domain) {
        if (domain.startsWith(HTTP)) {
            return HTTP;
        }
        if (domain.startsWith(HTTPS)) {
            return HTTPS;
        }
        // 其他未知协议 获取 :// 和前的内容
        int index = domain.indexOf(START);
        if (index > 0) {
            return domain.substring(0, index + 3);
        }
        return "";
    }

    /**
     * 获取域名
     * <p>
     * http(s)://www.baidu.com/xxx/xxx?s='xxx'
     * 转为
     * https://www.baidu.com/
     *
     * @param domain 域名
     * @return 域名
     */
    public static String getHostProtocol(String domain) {
        if (domain.endsWith(END)) {
            domain = domain.substring(0, domain.length() - 1);
        }
        String path = getUrlPath(domain);
        domain = domain.replace(path, "");
        String coverUrl = chineseCoverUrl(domain);
        Pattern pattern = Pattern.compile("(?i)https?://(?:www\\.)?([\\w.-]+)(?::\\d+)?");
        Matcher matcher = pattern.matcher(coverUrl);
        String hostUrl = domain;
        if (matcher.find()) {
            hostUrl = matcher.group(0);
        }
        hostUrl = urlCoverChinese(hostUrl);
        if (!hostUrl.endsWith(END)) {
            hostUrl = hostUrl + END;
        }
        return hostUrl;
    }

    /**
     * 获取域名
     * <p>
     * http(s)://www.baidu.com/xxx/xxx?s='xxx'
     * 转为
     * baidu.com
     *
     * @param domain 域名
     * @return 域名
     */
    public static String getHost(String domain) {
        if (domain.endsWith(END)) {
            domain = domain.substring(0, domain.length() - 1);
        }
        String path = getUrlPath(domain);
        domain = domain.replace(path, "");
        String coverUrl = chineseCoverUrl(domain);
        Pattern pattern = Pattern.compile("(?i)https?://(?:www\\.)?([\\w.-]+)(?::\\d+)?");
        Matcher matcher = pattern.matcher(coverUrl);
        String hostUrl = domain;
        if (matcher.find()) {
            // 匹配结果
            String match = matcher.group();
            // 去除协议
            hostUrl = match.replace(getProtocol(match), "");
        }
        hostUrl = urlCoverChinese(hostUrl);
        return hostUrl;
    }

    /**
     * 获取路径
     * <p>
     * http(s)://www.baidu.com/xxx/xxx?s='xxx'
     * 转为
     * /xxx/xxx?s='xxx'
     *
     * @param domain 域名
     * @return 路径
     */
    public static String getUrlPath(String domain) {
        String protocol = getProtocol(domain);
        domain = domain.replace(protocol, "");
        // 获取路径
        int index = domain.indexOf(END);
        if (index > 0) {
            domain = domain.substring(index);
        } else {
            domain = "";
        }
        return domain;
    }

    /**
     * 域名转ip
     *
     * @param domain 域名
     * @return 域名
     */
    public static List<String> domainCoverIp(String domain) {
        try {
            Resolver resolver = new SimpleResolver("114.114.114.114");
            Lookup lookup = new Lookup(domain);
            lookup.setResolver(resolver);
            Record[] records = lookup.run();
            List<String> ips = new ArrayList<>();
            if (lookup.getResult() == Lookup.SUCCESSFUL) {
                for (Record record : records) {
                    // IP 地址
                    if (record instanceof ARecord) {
                        ARecord aRecord = (ARecord) record;
                        ips.add(aRecord.getAddress().getHostAddress());
                    }
                }
            }
            return ips;
        } catch (UnknownHostException | TextParseException e) {
            e.printStackTrace();
        }
        return Lists.empty();
    }

    /**
     * 域名转ip
     *
     * @param domain 域名
     * @return ip
     */
    public static String domainToIp(String domain) {
        String host = getHost(domain);
        try {
            InetAddress address = InetAddress.getByName(host);
            return address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 是否为链接
     * 仅限 http(s):// 开头
     *
     * @param uri 链接
     * @return 是否为链接
     */
    public static boolean isUrl(String uri) {
        if (Strings.isBlank(uri)) {
            return false;
        }
        return uri.matches(URL_REGULAR);
    }

    /**
     * 格式化域名
     *
     * @param domain 域名
     * @return 格式化后的域名
     */
    public static String formatDomain(String domain) {
        // 匹配协议、域名、端口和路径的正则表达式
        final String regex = "^(https?://)?(www\\.)?([a-zA-Z0-9.-]+)(:[0-9]+)?(/.*)?$";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);

        // 匹配结果
        Matcher matcher = pattern.matcher(domain);

        if (matcher.matches()) {
            String protocol = matcher.group(1) != null ? matcher.group(1) : "http://";
            String wwwPrefix = matcher.group(2) != null ? matcher.group(2) : "";
            String domainName = matcher.group(3);
            String port = matcher.group(4) != null ? matcher.group(4) : "";
            String path = matcher.group(5) != null ? matcher.group(5) : "/";

            // 如果是80端口，则协议为http
            if (":80".equals(port)) {
                protocol = "http://";
            }
            // 如果是443端口，则协议为https
            if (":443".equals(port)) {
                protocol = "https://";
            }

            // 如果端口是80或443，则不显示
            if (":80".equals(port) || ":443".equals(port)) {
                port = "";
            }

            // 确保路径以斜杠结尾
            path = path.replaceAll("/$", "") + "/";

            return protocol + wwwPrefix + domainName + port + path;
        } else {
            return domain;
        }
    }

    /**
     * 提取顶级主域名
     *
     * @param formattedDomain 格式化后的域名
     * @return 顶级主域名
     */
    public static String topHostDomain(String formattedDomain) {
        // 使用java.net.URL解析域名
        java.net.URL url;
        try {
            url = new java.net.URL(formattedDomain);
        } catch (java.net.MalformedURLException e) {
            return formattedDomain;
        }

        // 提取主域名
        String hostDomain = url.getHost();

        // 如果最后两级在 chineseDomain 中，则提取最后三级
        String[] domainParts = hostDomain.split("\\.");
        String lastTwoLevels = "." + domainParts[domainParts.length - 2] + "." + domainParts[domainParts.length - 1];
        if (contains(lastTwoLevels)) {
            hostDomain = domainParts[domainParts.length - 3] + "." + domainParts[domainParts.length - 2] + "." + domainParts[domainParts.length - 1];
        } else {
            hostDomain = domainParts[domainParts.length - 2] + "." + domainParts[domainParts.length - 1];
        }

        return hostDomain;
    }

    private static boolean contains(String target) {
        for (String item : DomainUtils.CHINA_TOP_LEVEL_DOMAIN) {
            if (item.equals(target)) {
                return true;
            }
        }
        return false;
    }
}

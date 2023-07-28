package com.ms.core.base.info;

import java.net.IDN;
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

    /**
     * 中文域名转换ASCII
     *
     * @param domain 域名
     * @return 转换后的域名
     */
    public static String chineseCoverUrl(String domain) {
        String protocol = getProtocol(domain);
        String ascii = IDN.toASCII(domain);
        if (!protocol.isEmpty()) {
            ascii = protocol + ascii;
        }
        return ascii;
    }

    /**
     * 中文ASCII转换中文域名
     */
    public static String urlCoverChinese(String domain) {
        return IDN.toUnicode(domain);
    }

    /**
     * 获取顶级域名
     *
     * @param domain 域名
     * @return 顶级域名
     */
    public static String getTopLevelDomain(String domain) {
        // 获取域名
        System.err.println("----------------------------------");
        String host = getHost(domain);
        System.err.println(host);
        // 获取协议
        String protocol = getProtocol(domain);
        System.err.println(protocol);
        // 去除协议
        host = host.replace(protocol, "");
        System.err.println(host);
        System.err.println("----------------------------------");
        // 中国顶级域名判断
        boolean chineseTopDomain = isChinaTopDomain(host);
        if (chineseTopDomain) {
            String[] split = host.split(LEVEL);
            // 只保留最后 xxx.xxx.xxx 三级
            if (split.length > 3) {
                host = split[split.length - 3] + LEVEL + split[split.length - 2] + LEVEL + split[split.length - 1];
            }
        }
        return host;
    }

    public static boolean isChinaTopDomain(String host) {
        for (String chinaTopLevelDomain : CHINA_TOP_LEVEL_DOMAIN) {
            if (host.endsWith(chinaTopLevelDomain)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取协议
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
     * www.baidu.com
     *
     * @param domain 域名
     * @return 域名
     */
    public static String getHost(String domain) {
        Pattern pattern = Pattern.compile("(?i)(?:https?|ftp|udp|tcp)://(?:www\\.)?([\\w.-]+)");
        Matcher matcher = pattern.matcher(domain);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return domain;
    }


    public static void main(String[] args) {
        // String[] domain = {
        //         "http://www.baidu.com",
        //         "https://www.baidu.com",
        //         "https://www.baidu.com/",
        //         "https://hello.baidu.com/",
        //         "udp://hello.baidu.com/",
        //         "https://1.2.baidu.com/",
        //         "https://www.hello.baidu.com/",
        //         "tcp://baidu.com/",
        //         "https://baidu.com/",
        //         "https://www.baidu.com.cn/",
        //         "https://www.baidu.政府",
        //         "https://baidu.中国",
        // };
        String[] domain = {
                "https://www.baidu.政府",
        };
        for (String s : domain) {
            System.out.println(s + " -> " + getTopLevelDomain(s));
        }
    }

}
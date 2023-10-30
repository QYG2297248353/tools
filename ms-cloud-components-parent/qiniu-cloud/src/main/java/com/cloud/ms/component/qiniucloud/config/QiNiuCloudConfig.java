package com.cloud.ms.component.qiniucloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 七牛云配置类
 *
 * @author ms
 */
@Configuration
@ConfigurationProperties(prefix = "ms.qiniu")
public class QiNiuCloudConfig {

    /**
     * 七牛云的accessKey
     */
    private String accessKey = "8XgTFhsdOiXQRHSTzx_R8baS28mwUgYbSCuESKf7";

    /**
     * 七牛云的secretKey
     */
    private String secretKey = "vlyL-qL-iNDE6STDeURNsYFTIoQlWntV620zIDdL";

    /**
     * 七牛云的域名
     */
    private String domain = "http://qiniu.qyg2297248353.top/";


    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}

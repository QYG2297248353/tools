package com.ms.redis.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ms2297248353
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "ms.network.redis")
public class RedisProperties {

    /**
     * Redis服务器地址
     */
    private String host;

    /**
     * Redis服务器连接端口
     */
    private Integer port;

    /**
     * Redis服务器连接密码（默认为空）
     */
    private String password;

    /**
     * 连接超时时间（毫秒）
     */
    private Integer timeout = 1000;

    /**
     * 是否启用连接池 (默认启用)
     */
    private Boolean enablePool = true;

    /**
     * 连接池配置
     */
    private RedisPoolProperties poolConfig = new RedisPoolProperties();

    /**
     * 在获取连接的时候检查有效性, 默认false
     */
    private Boolean testOnBorrow = false;

    /**
     * 在空闲时检查有效性, 默认false
     */
    private Boolean testWhileIdle = false;

    /**
     * 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
     */
    private Long minEvictableIdleTimeMillis = 1800000L;

    /**
     * 每次逐出检查时 逐出的最大数目 默认3
     */
    private Integer numTestsPerEvictionRun = 3;

    /**
     * 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
     */
    private Long timeBetweenEvictionRunsMillis = -1L;

    /**
     * 连接空闲多久后释放, 默认1800000毫秒(30分钟)
     */
    private Long softMinEvictableIdleTimeMillis = 1800000L;

    /**
     * 在获取连接的时候检查有效性, 默认false
     */
    private Boolean testOnReturn = false;

    /**
     * 是否启用pool的jmx管理功能, 默认true
     */
    private Boolean jmxEnabled = true;

    /**
     * MBean ObjectName = "org.apache.commons.pool2:type=GenericObjectPool,name=" + name
     */
    private String jmxNamePrefix;

    /**
     * 是否启用后进先出, 默认true
     */
    private Boolean lifo = true;

    /**
     * 是否启用fairness, 默认false
     */
    private Boolean fairness = false;

    /**
     * 是否启用testOnCreate, 默认false
     */
    private Boolean testOnCreate = false;
}

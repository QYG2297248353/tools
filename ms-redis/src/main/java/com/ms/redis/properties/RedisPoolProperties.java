package com.ms.redis.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ms2297248353
 */
@Getter
@Setter
public class RedisPoolProperties {
    /**
     * 连接池最大连接数（使用负值表示没有限制）
     */
    private Integer maxTotal = 8;

    /**
     * 连接池最大阻塞等待时间（使用负值表示没有限制）
     */
    private Integer maxWaitMillis = 1000;

    /**
     * 连接池中的最大空闲连接
     */
    private Integer maxIdle = 8;

    /**
     * 连接池中的最小空闲连接
     */
    private Integer minIdle = 0;

    /**
     * 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
     */
    private Boolean blockWhenExhausted = true;
}

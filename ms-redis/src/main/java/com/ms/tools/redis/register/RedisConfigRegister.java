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

package com.ms.tools.redis.register;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.alibaba.fastjson2.JSON;
import com.ms.tools.core.base.format.StrFormatUtils;
import com.ms.tools.core.exception.base.MsToolsRuntimeException;
import com.ms.tools.redis.listener.AbstractSubReceiver;
import com.ms.tools.redis.properties.MsRedisProperties;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * 注册 Redis 配置
 *
 * @author ms2297248353
 */
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
@Configuration
public class RedisConfigRegister extends CachingConfigurerSupport {
    private final Logger log = Logger.getLogger(RedisConfigRegister.class.getName());

    @Resource
    private RedisProperties redisProperties;

    @Resource
    private MsRedisProperties msRedisProperties;

    @Resource
    private AbstractSubReceiver abstractSubReceiver;


    /**
     * 设置 redis 数据默认过期时间，默认7天
     * 设置@cacheable 序列化方式
     *
     * @return 缓存配置对象
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        RedisCacheConfiguration redisCacheConfiguration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer));
        configuration = redisCacheConfiguration.entryTtl(Duration.ofSeconds(msRedisProperties.getGlobalExpire()));
        return configuration;
    }

    /**
     * 自定义缓存key生成策略，默认将使用该策略
     * 使用方法 @Cacheable
     *
     * @return key生成策略
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(JSON.toJSONString(obj).hashCode());
            }
            return sb.toString();
        };
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        // 异常处理，当Redis发生异常时，打印日志，但是程序正常走
        String format = StrFormatUtils.format("初始化 -> [{}]", "Redis CacheErrorHandler");
        log.info(format);
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                String log = StrFormatUtils.format("Redis occur handleCacheGetError：key -> [{}]", key, e);
                RedisConfigRegister.this.log.warning(log);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                String log = StrFormatUtils.format("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", key, value, e);
                RedisConfigRegister.this.log.warning(log);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                String log = StrFormatUtils.format("Redis occur handleCacheEvictError：key -> [{}]", key, e);
                RedisConfigRegister.this.log.warning(log);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                String log = StrFormatUtils.format("Redis occur handleCacheClearError：", e);
                RedisConfigRegister.this.log.warning(log);
            }
        };
        return cacheErrorHandler;
    }

    /**
     * 为RedisTemplate配置Redis连接工厂实现
     * LettuceConnectionFactory实现了RedisConnectionFactory接口
     * 这里要注意的是，在构建LettuceConnectionFactory 时，如果不使用内置的destroyMethod，可能会导致Redis连接早于其它Bean被销毁
     *
     * @return 返回LettuceConnectionFactory
     */
    @Bean(destroyMethod = "destroy")
    public LettuceConnectionFactory lettuceConnectionFactory() {
        /**
         * 连接池配置
         */
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(redisProperties.getLettuce().getPool().getMaxIdle());
        poolConfig.setMinIdle(redisProperties.getLettuce().getPool().getMinIdle());
        poolConfig.setMaxTotal(redisProperties.getLettuce().getPool().getMaxActive());

        if (redisProperties.getCluster() == null) {
            /**
             * 单节点配置
             */
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
            redisStandaloneConfiguration.setHostName(redisProperties.getHost());
            redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
            redisStandaloneConfiguration.setDatabase(redisProperties.getDatabase());
            redisStandaloneConfiguration.setPort(redisProperties.getPort());
            return new LettuceConnectionFactory(redisStandaloneConfiguration, getLettuceClientConfiguration(poolConfig));
        } else {
            /**
             * 集群配置
             */
            List<String> clusterNodes = redisProperties.getCluster().getNodes();
            Set<RedisNode> nodes = new HashSet<>();
            clusterNodes.forEach(address -> {
                String host = address.split(":")[0].trim();
                int port = Integer.parseInt(address.split(":")[1]);
                nodes.add(new RedisNode(host, port));
            });
            RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
            clusterConfiguration.setClusterNodes(nodes);
            clusterConfiguration.setPassword(RedisPassword.of(redisProperties.getPassword()));
            clusterConfiguration.setMaxRedirects(redisProperties.getCluster().getMaxRedirects());
            return new LettuceConnectionFactory(clusterConfiguration, getLettuceClientConfiguration(poolConfig));
        }
    }

    /**
     * 开启自适应刷新
     * 配置LettuceClientConfiguration 包括线程池配置和安全项配置
     *
     * @param genericObjectPoolConfig common-pool2线程池
     * @return lettuceClientConfiguration
     */
    private LettuceClientConfiguration getLettuceClientConfiguration(GenericObjectPoolConfig genericObjectPoolConfig) {
        ClusterTopologyRefreshOptions topologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                .enableAllAdaptiveRefreshTriggers()
                .adaptiveRefreshTriggersTimeout(Duration.ofSeconds(25))
                .enablePeriodicRefresh(Duration.ofSeconds(20))
                .build();

        return LettucePoolingClientConfiguration.builder()
                .poolConfig(genericObjectPoolConfig)
                .clientOptions(ClusterClientOptions.builder().topologyRefreshOptions(topologyRefreshOptions).build())
                // 将appID传入连接，方便Redis监控中查看
                .clientName(msRedisProperties.getClientName() + "_lettuce")
                .build();
    }

    /**
     * 初始化监听器
     *
     * @param connectionFactory 连接工厂
     * @return Redis消息监听容器
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }

    /**
     * 消息监听器适配器
     *
     * @return 消息监听器适配器
     */
    @Bean
    MessageListenerAdapter listenerAdapter() {
        if (abstractSubReceiver == null) {
            final String msg = "请实现 com.ms.redis.listener.AbstractSubReceiver";
            log.warning(msg);
            throw new MsToolsRuntimeException(msg);
        }
        return new MessageListenerAdapter(abstractSubReceiver, "onMessage");
    }
}

package com.ms.tools.redis.register;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.ms.tools.components.fastjson.FastGlobalConfiguration;
import com.ms.tools.redis.properties.MsRedisProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * @author ms
 */
@Configuration
public class RedisTemplateConfig {
    @Resource
    private MsRedisProperties msRedisProperties;

    @Bean(name = "redisTemplate")
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        String[] autoType = msRedisProperties.getAutoType();
        for (String auto : autoType) {
            FastGlobalConfiguration.addAutoType(auto);
        }

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        StringRedisSerializer serializer = new StringRedisSerializer();
        template.setDefaultSerializer(serializer);
        template.setKeySerializer(serializer);
        template.setHashKeySerializer(serializer);

        // FastJson2JsonRedisSerializer fastJsonRedisSerializer = new FastJson2JsonRedisSerializer(Object.class);
        GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        template.setValueSerializer(genericFastJsonRedisSerializer);
        template.setHashValueSerializer(genericFastJsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }
}

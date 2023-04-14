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

package com.ms.annotation.manager;

import com.ms.core.base.basic.FormatUtils;
import com.ms.core.exception.base.MsLimiterRunException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class RedisLimiter implements LimiterManager {

    private static final Logger log = Logger.getLogger(RedisLimiter.class.getName());

    private final StringRedisTemplate stringRedisTemplate;

    public RedisLimiter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean tryAccess(Limiter limiter) {

        String key = limiter.getKey();
        if (StringUtils.isEmpty(key)) {
            throw new MsLimiterRunException("redis limiter key cannot be null");
        }

        List<String> keys = new ArrayList<>();
        keys.add(key);

        int seconds = limiter.getSeconds();
        int limitCount = limiter.getLimitNum();

        String luaScript = buildLuaScript();

        RedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript, Long.class);

        Long count = stringRedisTemplate.execute(redisScript, keys, "" + limitCount, "" + seconds);
        String format = FormatUtils.format("Access try count is {} for key={}", count, key);
        log.info(format);

        return count != null && count != 0;
    }

    /**
     * 构建redis lua脚本
     *
     * @return lua脚本
     */
    private String buildLuaScript() {
        StringBuilder luaString = new StringBuilder();
        luaString.append("local key = KEYS[1]");
        // 获取ARGV内参数Limit
        luaString.append("\nlocal limit = tonumber(ARGV[1])");
        // 获取key的次数
        luaString.append("\nlocal currentLimit = tonumber(redis.call('get', key) or \"0\")");
        luaString.append("\nif currentLimit + 1 > limit then");
        luaString.append("\nreturn 0");
        luaString.append("\nelse");
        // 自增长 1
        luaString.append("\n redis.call(\"INCRBY\", key, 1)");
        // 设置过期时间
        luaString.append("\nredis.call(\"EXPIRE\", key, ARGV[2])");
        luaString.append("\nreturn currentLimit + 1");
        luaString.append("\nend");
        return luaString.toString();
    }
}

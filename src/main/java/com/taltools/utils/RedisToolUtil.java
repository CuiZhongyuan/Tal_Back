package com.taltools.utils;

/**
 * Author by zhangzhijia, Email zhangzhijia@100tal.com, Date on 2020/4/7.
 * 作用：
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Author by zhangzhijia, Email zhangzhijia@100tal.com, Date on 2019/9/25.
 * 作用：redis单机分布式锁
 */
@Service
public class RedisToolUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 尝试获取分布式锁
     *
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public Boolean tryGetDistributedLock(String lockKey, String requestId, Long expireTime, TimeUnit timeUnit) {
        lockKey = generateLockKey(lockKey);
        Boolean b = redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, expireTime, timeUnit);
        return b != null && b;
    }

    /**
     * 释放分布式锁
     *
     * @param lockKey   锁
     * @param requestId 请求标识releaseDistributedLock
     * @return 是否释放成功
     */
    public boolean releaseDistributedLock(String lockKey, String requestId) {
        lockKey = generateLockKey(lockKey);
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(script);
        redisScript.setResultType(Long.class);
        Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), requestId);

        return RELEASE_SUCCESS.equals(result);

    }

    /**
     * 加一个lock目录
     *
     * @param key redis的key
     * @return lock:key
     */
    private String generateLockKey(String key) {
        return String.format("lock:%s", key);
    }
}

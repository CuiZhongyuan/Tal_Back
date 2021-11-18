package com.taltools.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil<T> {

    @Resource
    private RedisTemplate<String, T> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 获取指定key下所有键值对
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hGetAll(String key) {
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(key);
        return entries;
    }

    /**
     * 设置指定key下所有键值对
     *
     * @param key
     * @return
     */
    public void hPutAll(String key, Map<Object, Object> hkv, long second) {
        stringRedisTemplate.boundHashOps(key).putAll(hkv);
        stringRedisTemplate.boundHashOps(key).expire(second, TimeUnit.SECONDS);
    }

    public boolean hIsExist(String key) {
        Long size = stringRedisTemplate.opsForHash().size(key);
        if (size == null || size <= 0)
            return false;
        return true;
    }

    /**
     * 获取指定key的值
     *
     * @param k
     * @return
     */
    public T getStringValue(String k) {
        return redisTemplate.opsForValue().get(k);
    }

    /**
     * 设置key的值
     *
     * @param k
     * @param v
     */
    public void setStringValue(String k, T v) {
        redisTemplate.opsForValue().set(k, v);
    }

    /**
     * 写入redis数据
     *
     * @param k        key
     * @param v        value
     * @param l        时间
     * @param timeUnit 时间单位
     */
    public void setStringValue(String k, T v, long l, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(k, v, l, timeUnit);
    }

    /**
     * 写入redis数据
     *
     * @param k
     * @param v
     * @param l
     * @param timeUnit
     */
    public Boolean setNxSringValue(String k, T v, long l, TimeUnit timeUnit) {
        Boolean b = redisTemplate.opsForValue().setIfAbsent(k, v, l, timeUnit);
        return b != null && b;
    }

    /**
     * 设置key的字符串值并返回其旧值
     *
     * @param k
     * @param v
     */
    public T setOrUpdateStringValue(String k, T v) {
        return redisTemplate.opsForValue().getAndSet(k, v);
    }

    /**
     * 获取可以的list值
     *
     * @param k
     * @return
     */
    public List<T> getListValue(String k) {
        long size;
        if ((size = redisTemplate.opsForList().size(k)) > 0) {

            return redisTemplate.opsForList().range(k, 0, size);
        } else {
            return null;
        }
    }

    /**
     * 设置key的值是list
     *
     * @param k
     * @param list
     */
    public void setListvalue(String k, List<T> list) {
        redisTemplate.opsForList().rightPushAll(k, list);
    }

    /**
     * 设置过期时间
     */
    public void setOutTime(String key, long l, TimeUnit timeUnit) {
        redisTemplate.expire(key, l, timeUnit);
    }

    /**
     * 删除缓存
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 批量删除删除缓存
     */
    public long batchDelete(List<String> keys) {
        return redisTemplate.delete(keys);
    }

    /**
     * 集合添加元素
     */
    public void listRightPush(String key, T value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 集合删除list中一个值
     */
    public void removeList(String key, T value) {
        redisTemplate.opsForList().remove(key, 0, value);
    }

    /**
     * 存值
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lpush(String key, T value) {
        try {
            redisTemplate.opsForList().leftPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存值-list
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public boolean lpushList(String key, List<T> value) {
        try {
            redisTemplate.opsForList().leftPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 取值 - rpop：非阻塞式
     *
     * @param key 键
     * @return
     */
    public T rpop(String key) {
        try {
            return redisTemplate.opsForList().rightPop(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 取值 - 阻塞式
     *
     * @param key      键
     * @param timeout  超时时间
     * @param timeUnit 给定单元粒度的时间段
     * @return
     */
    public T brpop(String key, long timeout, TimeUnit timeUnit) {
        try {
            return redisTemplate.opsForList().rightPop(key, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 查看值
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     */
    public List<T> lrange(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查看值
     *
     * @param key 键
     * @return
     */
    public long lsize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0l;
        }
    }

    /**
     * 获取过期时间
     *
     * @param key
     * @param timeUnit
     * @return
     */
    public long getExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit == null ? TimeUnit.SECONDS : timeUnit);
    }

    /**
     * 计数
     *
     * @param key
     * @param v
     * @param expire
     */
    public void setIncrement(String key, T v, long expire) {
        redisTemplate.opsForValue().setIfAbsent(key, v, expire, TimeUnit.SECONDS);
        redisTemplate.opsForValue().increment(key);
    }

    public boolean getLock(String lockId, long seconds) {
        Boolean success = stringRedisTemplate.opsForValue().setIfAbsent(lockId, "lock", seconds, TimeUnit.SECONDS);
        return success != null && success;
    }

    public void releaseLock(String lockId) {
        stringRedisTemplate.delete(lockId);
    }
}


package com.yhyt.health.service.impl;

import com.yhyt.health.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author localadmin
 *
 */
@Repository
public class RedisService {
	
    @Autowired
    private  RedisTemplate<String, Object> redisTemplate;
    
    /**
     * 保存对象
     * @param key    key
     * @param doamin 对象
     * @param expire 过期时间(单位:秒),传入 -1 时表示不设置过期时间
     */
    public void put(String global,String key, Object t, long expire) {
    	redisTemplate.opsForHash().put(global, key, SerializeUtil.serialize(t));
        if (expire != -1) {
            redisTemplate.expire(global, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 删除
     *
     * @param key 传入key的名称
     */
    public void remove(String global,String key) {
        redisTemplate.opsForHash().delete(global, key);
    }

    /**
     * 查询
     *
     * @param key 查询的key
     * @return
     */
    public Object get(String global,String key) {
    	 byte[] value = (byte[]) redisTemplate.opsForHash().get(global, key);
        return SerializeUtil.unserialize(value);
    }

    /**
     * 获取当前redis库下所有对象
     *
     * @return
     */
    public List<Object> getAll(String global) {
        return redisTemplate.opsForHash().values(global);
    }

    /**
     * 查询查询当前redis库下所有key
     *
     * @return
     */
    public Set<Object> getKeys(String global) {
        return redisTemplate.opsForHash().keys(global);
    }

    /**
     * 判断key是否存在redis中
     *
     * @param key 传入key的名称
     * @return
     */
    public boolean isKeyExists(String global,String key) {
        return redisTemplate.opsForHash().hasKey(global, key);
    }

    /**
     * 查询当前key下缓存数量
     *
     * @return
     */
    public long count(String global) {
        return redisTemplate.opsForHash().size(global);
    }

    /**
     * 清空redis
     */
    public void empty(String global) {
        Set<Object> set = redisTemplate.opsForHash().keys(global);
        set.stream().forEach(key -> redisTemplate.opsForHash().delete(global, key));
    }

}

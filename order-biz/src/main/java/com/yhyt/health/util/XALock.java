package com.yhyt.health.util;

import org.springframework.data.redis.core.RedisTemplate;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.UUID;

/**
 * Created by localadmin on 17/9/16.
 */
public class XALock {
    private static final String KEYHOLDER_POSTFIX = "holder";

    public static RedisTemplate getRedisTemplate() {
        return RedisTemplateHolder.redisTemplate;
    }

    public static String lock(String key, long lockTime) {
        RedisTemplate redisTemplate = getRedisTemplate();
        long expireTime = System.currentTimeMillis() + lockTime;
        if (redisTemplate.opsForValue().setIfAbsent(key, expireTime)) {
            String holderKey = UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(key + KEYHOLDER_POSTFIX, holderKey);
            return holderKey;
        } else {
            long oldExpireTime = (long) redisTemplate.opsForValue().get(key);
            if (oldExpireTime < System.currentTimeMillis()) {
                expireTime = System.currentTimeMillis() + lockTime;
                long currentExpireTime = (long) redisTemplate.opsForValue().getAndSet(key, expireTime);
                if (currentExpireTime == oldExpireTime) {
                    String holderKey = UUID.randomUUID().toString();
                    redisTemplate.opsForValue().set(key + KEYHOLDER_POSTFIX, holderKey);
                    return holderKey;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    public static boolean unlock(String key, String holderKey) {
        RedisTemplate redisTemplate = getRedisTemplate();
        String oldHolderKey = (String) redisTemplate.opsForValue().get(key + KEYHOLDER_POSTFIX);
        if (oldHolderKey == null || !oldHolderKey.equals(holderKey)) {
            return false;
        }
        long oldExpireTime = (long) redisTemplate.opsForValue().get(key);
        if (oldExpireTime > System.currentTimeMillis()) {
            redisTemplate.delete(key);
            redisTemplate.delete(key + KEYHOLDER_POSTFIX);
            return true;
        } else {
            return true;
        }
    }

    private static class RedisTemplateHolder {
        static final RedisTemplate redisTemplate = getBean();

        private static RedisTemplate getBean() {
            return (RedisTemplate) ApplicationContextUtil.getBean("redisTemplate");
        }
    }

    private static Unsafe getMemStruc() throws IllegalAccessException {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe o = (Unsafe) field.get(null);
        return o;
    }

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
        {
            Unsafe memStruc = getMemStruc();
            Class<?> aClass = Class.forName("com.yhyt.health.model.OrderVo");
//        OrderDetail orderDetail = (OrderDetail) aClass;
            Field createTime = aClass.getDeclaredField("createTime");
            long l = memStruc.objectFieldOffset(createTime);
            System.out.println(l);
        }
    }
}

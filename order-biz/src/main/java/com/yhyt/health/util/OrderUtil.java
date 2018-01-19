package com.yhyt.health.util;

import com.yhyt.health.OrderBizApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Created by localadmin on 17/9/1.
 */
public class OrderUtil {

    public static String generateOrderNo() {
        return applyRules(getBaseOrderNO());
    }

    private static String applyRules(long baseOrderNo) {
        String value = String.valueOf(baseOrderNo);
        if (value.length() >= 8) {
            return value;
        } else {
            return String.format("%08d", baseOrderNo);
        }
    }

    private static long getBaseOrderNO() {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtil.getBean("redisTemplate");
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setLocation(new ClassPathResource("cardno.lua"));
        redisScript.setResultType(Long.class);
        String time = DateUtil.convert2String(new Date(), DateUtil.SHORT_DATE);
        return (long) redisTemplate.execute(redisScript, new StringRedisSerializer(), new StringRedisSerializer(), Arrays.asList("cardNo", "no", "time"), time, random4Number() + "");
    }

    private static int random4Number() {
        Random random = new Random(1000);
        return random.nextInt(10000 - 1000) + 1000;
    }

    public static void main(String[] args) {
        /*for (int i = 0; i < 10; i++) {

            System.out.printf(random4Number() + "\n");
        }*/
        /*ApplicationContext ctx = SpringApplication.run(OrderBizApplication.class);
        RedisTemplate redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setLocation(new ClassPathResource("cardno.lua"));
        redisScript.setResultType(Long.class);
        long cardno = (long) redisTemplate.execute(redisScript, new StringRedisSerializer(), new StringRedisSerializer(), Arrays.asList("cardNo", "no", "time"), "2017-09-07", "1000");*/
//        System.out.println(String.valueOf(getBaseOrderNO())+"--------------");
        System.out.println(applyRules(832424) + "--------------");
    }
}

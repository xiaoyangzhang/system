package com.yhyt.health.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created by localadmin on 17/9/4.
 */
@Service
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    public static Object getBean(String name){
        return ctx.getBean(name);
    }

    public static Object getBean(Class classType){
        return ctx.getBean(classType);
    }
}

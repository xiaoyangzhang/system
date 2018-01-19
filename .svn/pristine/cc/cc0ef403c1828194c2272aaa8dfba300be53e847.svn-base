package com.yhyt.health.spring;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class DataSourceAspect implements Ordered{
	
	 @Before("execution(* com.yhyt.health.service..*.*(..))")
     public void before(JoinPoint point){  
    	 
		Object target = point.getTarget();
		String method = point.getSignature().getName();
		Class<?>[] classz = target.getClass().getInterfaces();
		Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try {  
			Method m = classz[0].getMethod(method, parameterTypes);
			if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource data = m.getAnnotation(DataSource.class);  
				HandleDataSource.removeRouteKey();
                HandleDataSource.putDataSource(data.value());  
			}
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
	 
	 @Override
	 public int getOrder() {
	        return 1;
	    }
}



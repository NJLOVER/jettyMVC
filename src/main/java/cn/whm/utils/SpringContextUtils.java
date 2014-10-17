package cn.whm.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by wanghm on 2014/10/8.
 */
public class SpringContextUtils implements ApplicationContextAware{
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext(){
        return applicationContext;
    }
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }
    public static <T> T getBean(String name ,Class<T> clazz){
        return applicationContext.getBean(name,clazz);
    }
    public static Object getBeanByArgs(String name ,Object ... args){
        return applicationContext.getBean(name,args);
    }
    public static Object getBeanByClass(Class clazz){
        return applicationContext.getBean(clazz);
    }
}

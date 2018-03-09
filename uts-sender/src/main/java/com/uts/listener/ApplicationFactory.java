package com.uts.listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/3/6 0006.
 */
@Component
public class ApplicationFactory implements ApplicationContextAware {
    private static  ApplicationContext ctx = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(ApplicationFactory.ctx==null){
            ApplicationContext ctx=applicationContext;
        }
    }


    public static ApplicationContext getCtx(){
        return ctx;
    }

    public static Object getBean(String name){
      return   getCtx().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz){
        return getCtx().getBean(clazz);
    }

    public static <T> T getBean(String name,Class<T> clazz){
        return  getCtx().getBean(name,clazz);
    }
}

package com.jpa.bookmanager.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component //bean으로 등록
public class BeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtils.applicationContext = applicationContext;
        //applicationContext를 주입받음
    }

    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
        //clazz에 있는 빈을 리턴
    }
}

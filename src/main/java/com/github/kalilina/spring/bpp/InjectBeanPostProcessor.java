package com.github.kalilina.spring.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.Arrays;

@Component
public class InjectBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        var fields = bean.getClass().getDeclaredFields();
        Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(InjectBean.class))
                .forEach(field -> {
                    var objectToInject = applicationContext.getBean(field.getType());

//                    field.setAccessible(true);
//                    try {
//                        field.set(bean, objectToInject);
//                    } catch (IllegalAccessException e) {
//                        throw new RuntimeException(e);
//                    }

                    ReflectionUtils.makeAccessible(field);
                    ReflectionUtils.setField(field, bean, objectToInject);
                });
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

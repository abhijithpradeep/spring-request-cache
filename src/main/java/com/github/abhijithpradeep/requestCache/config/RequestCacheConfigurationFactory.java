package com.github.abhijithpradeep.requestCache.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;

public class RequestCacheConfigurationFactory implements BeanFactoryAware {

    private BeanFactory beanFactory;

    private IRequestCacheManager requestCacheManager;

    private KeyGenerator keyGenerator;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException{
        this.beanFactory = beanFactory;
    }


    public void init() throws Exception {
        try {
            RequestCacheConfigurer requestCacheConfigurator = beanFactory.getBean(RequestCacheConfigurer.class);
            if(requestCacheConfigurator.getRequestCacheManager() != null){
                requestCacheManager = requestCacheConfigurator.getRequestCacheManager();
            }
            if(requestCacheConfigurator.getKeyGenerator() != null){
                keyGenerator = requestCacheConfigurator.getKeyGenerator();
            }
            if(requestCacheManager == null){
                requestCacheManager = new ThreadlocalCacheManager();
            }
            if(keyGenerator == null){
                keyGenerator = new SimpleKeyGenerator();
            }
        }catch (NoSuchBeanDefinitionException ex){
            requestCacheManager = new ThreadlocalCacheManager();
            keyGenerator = new SimpleKeyGenerator();
        }
    }

    public IRequestCacheManager getRequestCacheManager() {
        return requestCacheManager;
    }

    public KeyGenerator getKeyGenerator() {
        return keyGenerator;
    }
}

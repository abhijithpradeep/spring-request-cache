package com.github.abhijithpradeep.requestCache.config;

import com.github.abhijithpradeep.requestCache.interceptor.RequestCacheAdvisor;
import com.github.abhijithpradeep.requestCache.interceptor.RequestCacheableInterceptor;
import com.github.abhijithpradeep.requestCache.interceptor.RequestInterceptor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by abhijithpradeep on 18/9/17.
 */
@Configuration
public class RequestCacheConfiguration {

    @Bean
    public RequestCacheAdvisor requestCacheAdvisor(){
        RequestCacheAdvisor requestCacheAdvisor = new RequestCacheAdvisor();
        requestCacheAdvisor.setRequestCacheableInterceptor(requestCacheableInterceptor());
        requestCacheAdvisor.setRequestInterceptor(requestInterceptor());
        return requestCacheAdvisor;
    }

    @Bean
    public RequestInterceptor requestInterceptor(){
        RequestInterceptor requestInterceptor = new RequestInterceptor();
        requestInterceptor.setRequestCacheConfigurationFactory(requestCacheConfigurationFactory());
        return requestInterceptor;
    }

    @Bean
    public RequestCacheableInterceptor requestCacheableInterceptor(){
        RequestCacheableInterceptor requestCacheableInterceptor = new RequestCacheableInterceptor();
        requestCacheableInterceptor.setRequestCacheConfigurationFactory(requestCacheConfigurationFactory());
        return requestCacheableInterceptor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean(initMethod = "init")
    public RequestCacheConfigurationFactory requestCacheConfigurationFactory(){
        return new RequestCacheConfigurationFactory();
    }

}

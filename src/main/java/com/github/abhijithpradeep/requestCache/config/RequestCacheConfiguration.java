package com.github.abhijithpradeep.requestCache.config;

import com.github.abhijithpradeep.requestCache.advisor.RequestCacheAdvisor;
import com.github.abhijithpradeep.requestCache.advisor.RequestCacheInterceptor;
import com.github.abhijithpradeep.requestCache.advisor.RequestCachePointcut;
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
        requestCacheAdvisor.setAdvice(requestCacheInterceptor());
        requestCacheAdvisor.setPointcut(requestCachePointcut());
        return requestCacheAdvisor;
    }

    @Bean
    public RequestCacheInterceptor requestCacheInterceptor(){
        return new RequestCacheInterceptor();
    }

    @Bean
    public RequestCachePointcut requestCachePointcut(){
        return new RequestCachePointcut();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

}

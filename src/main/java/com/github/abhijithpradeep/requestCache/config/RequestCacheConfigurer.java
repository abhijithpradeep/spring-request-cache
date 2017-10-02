package com.github.abhijithpradeep.requestCache.config;

import org.springframework.cache.interceptor.KeyGenerator;

public class RequestCacheConfigurer {

    private KeyGenerator keyGenerator;
    private IRequestCacheManager requestCacheManager;

    public KeyGenerator getKeyGenerator() {
        return keyGenerator;
    }

    public void setKeyGenerator(KeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    public IRequestCacheManager getRequestCacheManager() {
        return requestCacheManager;
    }

    public void setRequestCacheManager(IRequestCacheManager requestCacheManager) {
        this.requestCacheManager = requestCacheManager;
    }
}

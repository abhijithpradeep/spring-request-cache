package com.github.abhijithpradeep.requestCache.interceptor;

import com.github.abhijithpradeep.requestCache.annotation.RequestCacheable;
import com.github.abhijithpradeep.requestCache.config.RequestCacheConfigurationFactory;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cache.Cache;
import org.springframework.core.annotation.AnnotatedElementUtils;

public class RequestCacheableInterceptor {

    private RequestCacheConfigurationFactory requestCacheConfigurationFactory;

    public Object invoke(MethodInvocation invocation) throws Throwable {

        Object key = requestCacheConfigurationFactory.getKeyGenerator().generate(invocation,invocation.getMethod(),invocation.getMethod().getParameters());
        RequestCacheable lastCacheableAnnotation = AnnotatedElementUtils.findMergedAnnotation(invocation.getMethod(), RequestCacheable.class);
        String cacheName = lastCacheableAnnotation.value();
        if (cacheName == null || cacheName.isEmpty()) {
            cacheName = invocation.getMethod().toGenericString();
        }
        Cache cache = requestCacheConfigurationFactory.getRequestCacheManager().getCache(cacheName);
        Cache.ValueWrapper cachedObject = cache.get(key);
        if (cachedObject == null) {
            Object invokedObject = invocation.proceed();
            cache.put(key,invokedObject);
            return invokedObject;
        }
        Cache.ValueWrapper valueWrapper = cachedObject;
        return valueWrapper.get();
    }

    public void setRequestCacheConfigurationFactory(RequestCacheConfigurationFactory requestCacheConfigurationFactory) {
        this.requestCacheConfigurationFactory = requestCacheConfigurationFactory;
    }
}

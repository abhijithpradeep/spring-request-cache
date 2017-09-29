package com.github.abhijithpradeep.requestCache.advisor;

import com.github.abhijithpradeep.requestCache.annotations.Request;
import com.github.abhijithpradeep.requestCache.annotations.RequestCacheable;
import com.github.abhijithpradeep.requestCache.cache.ThreadlocalCacheManager;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.util.Set;

/**
 * Created by abhijithpradeep on 21/9/17.
 */
public class RequestCacheInterceptor implements MethodInterceptor {

    ThreadlocalCacheManager threadlocalCacheManager = new ThreadlocalCacheManager();

    public Object invoke(MethodInvocation invocation) throws Throwable{
        Set<Request> requests = AnnotatedElementUtils.findAllMergedAnnotations(invocation.getMethod(),Request.class);
        if(requests != null && !requests.isEmpty()) {
            Request lastRequest = requests.iterator().next();
            String requestName = lastRequest.value();
            if (requestName == null || requestName.isEmpty()) {
                requestName = invocation.getMethod().toGenericString();
            }
            threadlocalCacheManager.startRequest(requestName);
            Object returnObject = invocation.proceed();
            threadlocalCacheManager.endRequest(requestName);
            return returnObject;
        }

        System.out.print("Starting caching in request : " + threadlocalCacheManager.getRequestName());
        Object simpleKey = SimpleKeyGenerator.generateKey(invocation.getMethod().getParameters());
        Set<RequestCacheable> requestCacheables = AnnotatedElementUtils.findAllMergedAnnotations(invocation.getMethod(),RequestCacheable.class);
        RequestCacheable lastCacheableAnnotation = requestCacheables.iterator().next();
        String cacheName = lastCacheableAnnotation.value();
        if(cacheName == null){
            cacheName = invocation.getMethod().toGenericString();
        }
        Cache cache = threadlocalCacheManager.getCache(cacheName);
        Object cachedObject = cache.get(simpleKey);
        if(cachedObject == null) {
            return invocation.proceed();
        }
        Cache.ValueWrapper valueWrapper = (Cache.ValueWrapper)cachedObject;
        return valueWrapper.get();
    }




}

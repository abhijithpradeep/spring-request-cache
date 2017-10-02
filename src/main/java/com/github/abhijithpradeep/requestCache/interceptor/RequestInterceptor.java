package com.github.abhijithpradeep.requestCache.interceptor;

import com.github.abhijithpradeep.requestCache.annotation.Request;
import com.github.abhijithpradeep.requestCache.config.RequestCacheConfigurationFactory;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotatedElementUtils;

public class RequestInterceptor {

    private RequestCacheConfigurationFactory requestCacheConfigurationFactory;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Request lastRequest = AnnotatedElementUtils.findMergedAnnotation(invocation.getMethod(), Request.class);
        String requestName = lastRequest.value();
        if (requestName == null || requestName.isEmpty()) {
            requestName = invocation.getMethod().toGenericString();
        }
        requestCacheConfigurationFactory.getRequestCacheManager().startRequest(requestName);
        Object returnObject = invocation.proceed();
        requestCacheConfigurationFactory.getRequestCacheManager().endRequest(requestName);
        return returnObject;
    }

    public void setRequestCacheConfigurationFactory(RequestCacheConfigurationFactory requestCacheConfigurationFactory) {
        this.requestCacheConfigurationFactory = requestCacheConfigurationFactory;
    }
}

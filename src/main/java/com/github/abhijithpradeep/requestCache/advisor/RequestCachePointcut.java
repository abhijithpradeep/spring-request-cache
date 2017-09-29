package com.github.abhijithpradeep.requestCache.advisor;

import com.github.abhijithpradeep.requestCache.annotations.Request;
import com.github.abhijithpradeep.requestCache.annotations.RequestCacheable;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by abhijithpradeep on 21/9/17.
 */
public class RequestCachePointcut extends StaticMethodMatcherPointcut{

    public boolean matches(Method method, Class<?> parentClass){
        Set<RequestCacheable> requestCacheables = AnnotatedElementUtils.findAllMergedAnnotations(method,RequestCacheable.class);
        Set<Request> requests = AnnotatedElementUtils.findAllMergedAnnotations(method,Request.class);
        return !requestCacheables.isEmpty() || !requests.isEmpty();
    }

}

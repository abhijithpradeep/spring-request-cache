package com.github.abhijithpradeep.requestCache.interceptor;

import com.github.abhijithpradeep.requestCache.annotation.Request;
import com.github.abhijithpradeep.requestCache.annotation.RequestCacheable;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by abhijithpradeep on 21/9/17.
 */
public class RequestCacheAdvisor extends AbstractPointcutAdvisor{

    private RequestCacheableInterceptor requestCacheableInterceptor;
    private RequestInterceptor requestInterceptor;

    public Pointcut getPointcut(){
        return new StaticMethodMatcherPointcut(){
            @Override
            public boolean matches(Method method, Class<?> parentClass){
                Set<RequestCacheable> requestCacheables = AnnotatedElementUtils.findAllMergedAnnotations(method,RequestCacheable.class);
                Set<Request> requests = AnnotatedElementUtils.findAllMergedAnnotations(method,Request.class);
                return !requestCacheables.isEmpty() || !requests.isEmpty();
            }
        };
    }

    public Advice getAdvice(){

        return new MethodInterceptor(){
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable{
                Set<RequestCacheable> requestCacheables = AnnotatedElementUtils.findAllMergedAnnotations(invocation.getMethod(),RequestCacheable.class);
                if(!requestCacheables.isEmpty()){
                    return requestCacheableInterceptor.invoke(invocation);
                }else {
                    return requestInterceptor.invoke(invocation);
                }
            }
        };

    }

    public void setRequestCacheableInterceptor(RequestCacheableInterceptor requestCacheableInterceptor) {
        this.requestCacheableInterceptor = requestCacheableInterceptor;
    }

    public void setRequestInterceptor(RequestInterceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
    }
}

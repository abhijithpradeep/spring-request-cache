package com.github.abhijithpradeep.requestCache.advisor;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

/**
 * Created by abhijithpradeep on 21/9/17.
 */
public class RequestCacheAdvisor extends AbstractPointcutAdvisor{

    private Pointcut pointcut;
    private Advice advice;

    public Pointcut getPointcut(){
        return this.pointcut;
    }

    public Advice getAdvice(){
        return this.advice;
    }

    public void setPointcut(Pointcut pointcut) {
        this.pointcut = pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}

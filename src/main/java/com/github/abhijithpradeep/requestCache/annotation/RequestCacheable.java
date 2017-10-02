package com.github.abhijithpradeep.requestCache.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * Created by abhijithpradeep on 6/9/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface RequestCacheable {

    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";

}

package com.github.abhijithpradeep.requestCache.annotations;

import java.lang.annotation.*;

/**
 * Created by abhijithpradeep on 6/9/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface Request {

    String value() default "";

}

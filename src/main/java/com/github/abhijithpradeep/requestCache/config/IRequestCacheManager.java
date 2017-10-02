package com.github.abhijithpradeep.requestCache.config;

import org.springframework.cache.Cache;

/**
 * Created by abhijithpradeep on 24/9/17.
 */
public interface IRequestCacheManager {

    Cache getCache(String name);

    void startRequest(String name);

    void endRequest(String name);

    String getRequestName();

}

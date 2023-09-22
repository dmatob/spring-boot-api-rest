package com.dmatob.sandbox.springboot.infrastructure.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfiguration {

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("articles", "article");
	}
}

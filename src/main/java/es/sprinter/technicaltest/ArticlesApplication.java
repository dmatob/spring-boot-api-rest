package es.sprinter.technicaltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@EnableCaching
@SpringBootApplication
@PropertySource(value = { "classpath:application-persistence.properties" })
public class ArticlesApplication {

	public static void main(String [] args){
        SpringApplication.run(ArticlesApplication.class, args);
    }
	
	@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("articles");
    }
}

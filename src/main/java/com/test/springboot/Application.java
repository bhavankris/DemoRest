package com.test.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Bhavan on 6/19/2017.
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public CacheManager getChacheManager(){
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("Employees");
        return  cacheManager;
    }
}

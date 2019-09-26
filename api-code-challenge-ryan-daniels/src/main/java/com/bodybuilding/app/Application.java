package com.bodybuilding.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication(scanBasePackages={"com.bodybuilding.cache", "com.bodybuilding.controllers", "com.bodybuilding.models"})
@EnableCaching
public class Application {
	
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}

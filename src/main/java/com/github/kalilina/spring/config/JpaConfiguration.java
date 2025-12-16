package com.github.kalilina.spring.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Conditional(JpaCondition.class)
@Configuration
public class JpaConfiguration {

    @PostConstruct
    public void init() {
        System.out.println("JpaConfiguration is initialized");
    }
}

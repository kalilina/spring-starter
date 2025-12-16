package com.github.kalilina.spring;

import com.github.kalilina.spring.config.DatabaseProperties;
import com.github.kalilina.spring.database.pool.ConnectionPool;
import com.github.kalilina.spring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.SpringProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
@Slf4j
public class ApplicationRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationRunner.class, args);
        System.out.println(context.getEnvironment().getProperty("db.username"));
        System.out.println(SpringProperties.getProperty("test.msg")); // scan files only with prefix "spring."
        System.out.println(context.getBean(DatabaseProperties.class));
        System.out.println(context.getBean("connectionPool2", ConnectionPool.class));
    }
}

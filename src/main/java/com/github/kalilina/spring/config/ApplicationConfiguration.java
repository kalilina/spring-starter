package com.github.kalilina.spring.config;

import com.github.kalilina.spring.database.pool.ConnectionPool;
import com.github.kalilina.spring.database.repository.UserRepository;
import com.github.kalilina.web.config.WebConfiguration;
import org.springframework.context.annotation.*;

@Import(WebConfiguration.class) // add file from directory that is not scanned
@ImportResource("classpath:application.xml")
@Configuration
@ComponentScan(value = "com.github.kalilina.spring")
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

    @Bean(name = "connectionPool1")
    public ConnectionPool connectionPool1() {
        return new ConnectionPool("postgres:5432", "cust", "om", 5);
    }

    @Bean
    public ConnectionPool connectionPool2() {
        return new ConnectionPool("---", "mysql", "123", 100);
    }

    @Bean
    @Profile("web|test")
    public UserRepository userRepository() {
        return new UserRepository(connectionPool2(), "derevo", null);
    }
}

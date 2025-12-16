package com.github.kalilina.spring.database.pool;

import jakarta.annotation.PostConstruct;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Setter
@ToString
@Component
@Slf4j
public class ConnectionPool {

    private String url;
    private String username;
    private String password;
    private Integer poolSize;

    public ConnectionPool(@Value("${db.url}") String url,
                          @Value("${db.username}") String username,
                          @Value("${db.password}") String password,
                          @Value("${db.pool.size}") Integer poolSize) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.poolSize = poolSize;
    }

    @PostConstruct
    public void init() {
        log.info("ConnectionPool initialized with values: {}", this.toString());
    }
}

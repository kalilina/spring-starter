package com.github.kalilina.spring.database.pool;

import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Setter
@ToString
@Component
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
}

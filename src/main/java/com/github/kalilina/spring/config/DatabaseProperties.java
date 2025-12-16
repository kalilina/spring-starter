package com.github.kalilina.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "db")
public record DatabaseProperties (String driver,
                                  String url,
                                  String username,
                                  String password,
                                  PoolProperties pool,
                                  List<PoolProperties> pools,
                                  Map<String, Object> properties) {

    public static record PoolProperties(Integer size,
                                        Integer timeout) {
    }
}

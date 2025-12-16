package com.github.kalilina.integration;

import com.github.kalilina.spring.database.pool.ConnectionPool;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.SpyBean;

@TestConfiguration
public class TestApplicationRunner {

    @SpyBean(name = "connectionPool2")
    private ConnectionPool connectionPool;
}

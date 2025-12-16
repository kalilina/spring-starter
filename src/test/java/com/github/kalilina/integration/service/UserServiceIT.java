package com.github.kalilina.integration.service;

import com.github.kalilina.integration.annotation.IT;
import com.github.kalilina.spring.database.pool.ConnectionPool;
import com.github.kalilina.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestConstructor;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserServiceIT {

    private final UserService userService;

    @Test
    public void test() {

    }
}

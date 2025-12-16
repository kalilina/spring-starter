package com.github.kalilina.integration.ioc;

import com.github.kalilina.spring.config.ApplicationConfiguration;
import com.github.kalilina.spring.database.repository.UserRepository;
import com.github.kalilina.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {

    @Test
    public void dependencyInjectionInUserService() {
        var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        var userRepository1 = context.getBean(UserRepository.class);
        System.out.println(userRepository1);

        var userService = context.getBean(UserService.class);
        System.out.println(userService);
        context.close();
    }
}

package com.github.kalilina.ioc;

import com.github.kalilina.spring.config.ApplicationConfiguration;
import com.github.kalilina.spring.database.repository.UserRepository;
import com.github.kalilina.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {

    private static final String SPRING_XML_CONFIG_PATH = "classpath:application.xml";

    @Test
    public void dependencyInjectionInUserService() {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(SPRING_XML_CONFIG_PATH);
        var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        var userRepository1 = context.getBean(UserRepository.class);
        System.out.println(userRepository1);

        var userService = context.getBean(UserService.class);
        System.out.println(userService);
        context.close();
    }
}

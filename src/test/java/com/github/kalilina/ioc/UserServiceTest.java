package com.github.kalilina.ioc;

import com.github.kalilina.database.repository.UserRepository;
import com.github.kalilina.service.UserService;
import com.github.kalilina.utils.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {

    private static final String SPRING_XML_CONFIG_PATH = "classpath:application.xml";

    @Test
    public void dependencyInjectionInUserService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(SPRING_XML_CONFIG_PATH);
        var userRepository1 = context.getBean("r1", UserRepository.class);
        var userRepository2 = context.getBean("r2", UserRepository.class);
        System.out.println(userRepository1);
        System.out.println(userRepository2);

        var userService = context.getBean("us1", UserService.class);
        System.out.println(userService);
        context.close(); // trigger destroy method
    }
}

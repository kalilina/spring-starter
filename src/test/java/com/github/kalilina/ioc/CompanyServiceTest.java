package com.github.kalilina.ioc;

import com.github.kalilina.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CompanyServiceTest {

    private static final String SPRING_XML_CONFIG_PATH = "classpath:application.xml";

    @Test
    public void getBeanFromXmlFile() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(SPRING_XML_CONFIG_PATH);

        var companyService = context.getBean("cs1", CompanyService.class);
        System.out.println(companyService);

        context.close();
    }
}

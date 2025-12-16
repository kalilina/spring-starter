package com.github.kalilina.integration.listener;

import com.github.kalilina.spring.config.ApplicationConfiguration;
import com.github.kalilina.spring.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CompanyListenerTest {

    @Test
    public void entityEvent() {
        try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
            CompanyService companyService = context.getBean(CompanyService.class);
            companyService.findById(20);
        }
    }
}

package com.github.kalilina.integration.listener;

import com.github.kalilina.integration.annotation.IT;
import com.github.kalilina.spring.config.ApplicationConfiguration;
import com.github.kalilina.spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.TestConstructor;

@IT
@RequiredArgsConstructor
public class CompanyListenerTest {

    private final CompanyService companyService;

    @Test
    public void entityEvent() {
        companyService.findById(20);
    }
}

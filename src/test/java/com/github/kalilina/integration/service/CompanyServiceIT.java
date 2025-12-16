package com.github.kalilina.integration.service;

import com.github.kalilina.integration.annotation.IT;
import com.github.kalilina.spring.config.DatabaseProperties;
import com.github.kalilina.spring.dto.CompanyReadDto;
import com.github.kalilina.spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestConstructor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CompanyServiceIT {

    private static final Integer COMPANY_ID = 1;

    private final CompanyService companyService;
    private final DatabaseProperties databaseProperties;

    @Test
    public void findById() {
        Optional<CompanyReadDto> maybeCompanyDto = companyService.findById(COMPANY_ID);

        assertTrue(maybeCompanyDto.isPresent());
        assertEquals(COMPANY_ID, maybeCompanyDto.get().id());

    }
}

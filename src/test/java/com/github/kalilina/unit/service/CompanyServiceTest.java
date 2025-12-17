package com.github.kalilina.unit.service;

import com.github.kalilina.spring.database.entity.Company;
import com.github.kalilina.spring.database.repository.CompanyRepository;
import com.github.kalilina.spring.dto.CompanyReadDto;
import com.github.kalilina.spring.listener.EntityEvent;
import com.github.kalilina.spring.mapper.CompanyMapper;
import com.github.kalilina.spring.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    private static final Integer COMPANY_ID = 1;

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private CompanyMapper companyMapper;
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    @InjectMocks
    private CompanyService companyService;

    @Test
    public void findById() {
        // Arrange
        Company companyEntity = Company.builder()
                .id(COMPANY_ID)
                .name("Sasung")
                .build();
        CompanyReadDto companyDto = new CompanyReadDto(COMPANY_ID, "Sasung");

        when(companyRepository.findById(COMPANY_ID))
                .thenReturn(Optional.of(companyEntity));
        when(companyMapper.toReadDto(companyEntity))
                .thenReturn(companyDto);

        // Act
        Optional<CompanyReadDto> maybeCompanyDto = companyService.findById(COMPANY_ID);

        // Assert
        assertTrue(maybeCompanyDto.isPresent());
        assertEquals(companyDto, maybeCompanyDto.get());

        verify(companyRepository, times(1)).findById(COMPANY_ID);
        verify(companyMapper, times(1)).toReadDto(companyEntity);
        verify(applicationEventPublisher).publishEvent(any(EntityEvent.class));
    }
}

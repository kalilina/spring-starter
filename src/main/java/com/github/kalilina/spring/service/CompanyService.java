package com.github.kalilina.spring.service;

import com.github.kalilina.spring.database.entity.Company;
import com.github.kalilina.spring.database.repository.CompanyRepository;
import com.github.kalilina.spring.dto.CompanyReadDto;
import com.github.kalilina.spring.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public Optional<CompanyReadDto> findById(int id) {
        return companyRepository
                .findById(id)
                .map(companyMapper::toReadDto);
    }
}

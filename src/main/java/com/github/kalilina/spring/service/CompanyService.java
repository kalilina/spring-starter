package com.github.kalilina.spring.service;

import com.github.kalilina.spring.database.repository.CompanyRepository;
import com.github.kalilina.spring.dto.CompanyReadDto;
import com.github.kalilina.spring.listener.AccessType;
import com.github.kalilina.spring.listener.EntityEvent;
import com.github.kalilina.spring.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    public List<CompanyReadDto> findAll() {
        return companyRepository.findAll().stream()
                .map(companyMapper::toReadDto)
                .toList();
    }

    public Optional<CompanyReadDto> findById(int id) {
        return companyRepository
                .findById(id)
                .map(entity -> {
                    applicationEventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                    return companyMapper.toReadDto(entity);
                });
    }
}

package com.github.kalilina.spring.mapper;

import com.github.kalilina.spring.database.entity.Company;
import com.github.kalilina.spring.dto.CompanyReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyReadDto toReadDto(Company companyEntity);
}

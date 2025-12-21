package com.github.kalilina.spring.mapper;

import com.github.kalilina.spring.database.entity.Company;
import com.github.kalilina.spring.dto.CompanyReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompanyMapper {

    CompanyReadDto toReadDto(Company companyEntity);

    Company toEntity(CompanyReadDto companyReadDto);
}

package com.github.kalilina.spring.mapper;

import com.github.kalilina.spring.database.entity.Company;
import com.github.kalilina.spring.dto.CompanyReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper {

    CompanyReadDto toReadDto(Company companyEntity);

    Company toEntity(CompanyReadDto companyReadDto);
}

package com.github.kalilina.spring.mapper;

import com.github.kalilina.spring.database.entity.PersonalInfo;
import com.github.kalilina.spring.dto.PersonalInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonalInfoMapper {

    PersonalInfo toEntity(PersonalInfoDto personalInfoDto);

    PersonalInfoDto toDto(PersonalInfo personalInfo);
}

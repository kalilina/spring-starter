package com.github.kalilina.spring.dto;

import com.github.kalilina.spring.database.entity.Role;
import lombok.Builder;
import lombok.experimental.FieldNameConstants;

@Builder
@FieldNameConstants
public record UserCreateEditDto(String username,
                                PersonalInfoDto personalInfo,
                                Role role,
                                Integer companyId) {
}

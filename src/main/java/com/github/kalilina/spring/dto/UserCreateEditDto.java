package com.github.kalilina.spring.dto;

import com.github.kalilina.spring.database.entity.Role;
import lombok.Builder;

@Builder
public record UserCreateEditDto(String username,
                                PersonalInfoDto personalInfo,
                                Role role,
                                Integer companyId) {
}

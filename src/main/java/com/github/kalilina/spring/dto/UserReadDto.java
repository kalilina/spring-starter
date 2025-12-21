package com.github.kalilina.spring.dto;

import com.github.kalilina.spring.database.entity.Role;
import lombok.Builder;

@Builder
public record UserReadDto(Long id,
                          String username,
                          PersonalInfoDto personalInfo,
                          Role role,
                          CompanyReadDto company) {
}

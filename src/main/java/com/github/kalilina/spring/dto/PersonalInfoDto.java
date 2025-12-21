package com.github.kalilina.spring.dto;

import com.github.kalilina.spring.database.entity.Birthday;
import lombok.Builder;

@Builder
public record PersonalInfoDto(Birthday birthDate,
                              String firstname,
                              String lastname) {
}

package com.github.kalilina.spring.dto;

import com.github.kalilina.spring.database.entity.Birthday;
import lombok.Builder;
import lombok.experimental.FieldNameConstants;

@Builder
@FieldNameConstants
public record PersonalInfoDto(Birthday birthDate,
                              String firstname,
                              String lastname) {
}

package com.github.kalilina.spring.dto;

import com.github.kalilina.spring.database.entity.Birthday;
import com.github.kalilina.spring.validator.PersonalInfo;
import lombok.Builder;
import lombok.experimental.FieldNameConstants;

@Builder
@FieldNameConstants
@PersonalInfo
public record PersonalInfoDto(Birthday birthDate,
                              String firstname,
                              String lastname) {
}

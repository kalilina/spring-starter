package com.github.kalilina.spring.dto;

import com.github.kalilina.spring.database.entity.Birthday;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.experimental.FieldNameConstants;

@Builder
@FieldNameConstants
public record PersonalInfoDto(Birthday birthDate,

                              @NotBlank
                              @Size(min = 4, max = 30)
                              String firstname,

                              @Size(min = 4, max = 30)
                              String lastname) {
}

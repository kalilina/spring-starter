package com.github.kalilina.spring.dto;

import com.github.kalilina.spring.database.entity.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

@Builder
@FieldNameConstants
public record UserCreateEditDto(@NotBlank
                                @Email
                                String username,

                                @NotNull
                                @Valid
                                PersonalInfoDto personalInfo,

                                MultipartFile image,

                                @NotNull
                                Role role,

                                @NotNull
                                Integer companyId) {
}

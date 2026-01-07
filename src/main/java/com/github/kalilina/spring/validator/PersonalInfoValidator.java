package com.github.kalilina.spring.validator;

import com.github.kalilina.spring.dto.PersonalInfoDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import static org.springframework.util.StringUtils.hasText;

@Component
public class PersonalInfoValidator implements ConstraintValidator<PersonalInfo, PersonalInfoDto> {

    @Override
    public boolean isValid(PersonalInfoDto personalInfo, ConstraintValidatorContext context) {
        return hasText(personalInfo.firstname()) || hasText(personalInfo.lastname());
    }
}

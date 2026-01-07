package com.github.kalilina.spring.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PersonalInfoValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonalInfo {

    String message() default "firstname or lastname should be filled in";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

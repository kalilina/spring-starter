package com.github.kalilina.spring.dto;

import com.github.kalilina.spring.database.entity.Birthday;

public record PersonalInfoFilter(String firstname,
                                 String lastname,
                                 Birthday birthDate) {
}

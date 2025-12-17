package com.github.kalilina.spring.database.entity;

import com.github.kalilina.spring.database.converter.BirthdayConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Embeddable
public class PersonalInfo {

    private Birthday birthDate;
    private String firstname;
    private String lastname;
}

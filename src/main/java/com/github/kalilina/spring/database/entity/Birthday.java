package com.github.kalilina.spring.database.entity;

import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.time.LocalDate;

@FieldNameConstants
public record Birthday(LocalDate birthday) implements Serializable {
}

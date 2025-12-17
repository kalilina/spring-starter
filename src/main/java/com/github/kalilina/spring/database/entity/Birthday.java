package com.github.kalilina.spring.database.entity;

import java.io.Serializable;
import java.time.LocalDate;

public record Birthday(LocalDate birthday) implements Serializable {
}

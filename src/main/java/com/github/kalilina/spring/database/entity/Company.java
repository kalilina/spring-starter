package com.github.kalilina.spring.database.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
public class Company {
    Integer id;
    String name;
}

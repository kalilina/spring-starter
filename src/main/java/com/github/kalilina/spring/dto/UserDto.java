package com.github.kalilina.spring.dto;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@ToString
public class UserDto {

    private Long id;
    private String name;

    public UserDto(@Value("2") Long id,
                   @Value("using @Value") String name) {
        this.id = id;
        this.name = name;
    }
}

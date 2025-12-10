package com.github.kalilina.spring.mapper;

import com.github.kalilina.spring.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ToString
public class UserMapper {

    private final UserDto userDto;
}

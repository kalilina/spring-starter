package com.github.kalilina.spring.service;

import com.github.kalilina.spring.database.repository.UserRepository;
import com.github.kalilina.spring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ToString
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
}

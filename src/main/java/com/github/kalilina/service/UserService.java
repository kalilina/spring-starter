package com.github.kalilina.service;

import com.github.kalilina.database.repository.UserRepository;
import com.github.kalilina.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
}

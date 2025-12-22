package com.github.kalilina.spring.service;

import com.github.kalilina.spring.database.repository.CompanyRepository;
import com.github.kalilina.spring.database.repository.UserRepository;
import com.github.kalilina.spring.dto.UserCreateEditDto;
import com.github.kalilina.spring.dto.UserReadDto;
import com.github.kalilina.spring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final UserMapper userMapper;

    public List<UserReadDto> findAll() {
        return userRepository.findAllWithCompany().stream()
                .map(userMapper::toReadDto)
                .toList();
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findByIdWithCompany(id)
                .map(userMapper::toReadDto);
    }

    @Transactional
    public UserReadDto save(UserCreateEditDto userCreateDto) {
        return Optional.ofNullable(userCreateDto)
                .map(userDto -> userMapper.toEntity(userDto, companyRepository))
                .map(userRepository::save)
                .map(userMapper::toReadDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userEditDto) {
        return userRepository.findById(id)
                .map(userEntity -> {
                    userMapper.updateEntity(userEditDto, userEntity, companyRepository);
                    return userEntity;
                })
                .map(userRepository::saveAndFlush)
                .map(userMapper::toReadDto);
    }

    @Transactional
    public boolean delete (Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}

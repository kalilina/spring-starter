package com.github.kalilina.spring.service;

import com.github.kalilina.spring.database.repository.CompanyRepository;
import com.github.kalilina.spring.database.repository.UserRepository;
import com.github.kalilina.spring.dto.QPredicate;
import com.github.kalilina.spring.dto.UserCreateEditDto;
import com.github.kalilina.spring.dto.UserFilter;
import com.github.kalilina.spring.dto.UserReadDto;
import com.github.kalilina.spring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.github.kalilina.spring.database.entity.QUser.user;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final UserMapper userMapper;

    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
        QPredicate qPredicate = QPredicate.builder();
        if (filter.personalInfo() != null) {
            qPredicate.add(filter.personalInfo().firstname(), user.personalInfo.firstname::containsIgnoreCase)
                    .add(filter.personalInfo().lastname(), user.personalInfo.lastname::containsIgnoreCase);
        }
        var predicate = qPredicate.buildAnd();

        return userRepository.findAll(predicate, pageable)
                .map(userMapper::toReadDto);
    }

    public List<UserReadDto> findAll(UserFilter filter) {
        return userRepository.findAllByFilter(filter).stream()
                .map(userMapper::toReadDto)
                .toList();
    }

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

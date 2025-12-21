package com.github.kalilina.integration.mapper;

import com.github.kalilina.integration.annotation.IT;
import com.github.kalilina.spring.database.entity.*;
import com.github.kalilina.spring.database.repository.CompanyRepository;
import com.github.kalilina.spring.dto.PersonalInfoDto;
import com.github.kalilina.spring.dto.UserCreateEditDto;
import com.github.kalilina.spring.dto.UserReadDto;
import com.github.kalilina.spring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserMapperIT {

    private final UserMapper userMapper;
    private final CompanyRepository companyRepository;

    @Test
    public void toEntityTest() {
        int companyId = 1;
        UserCreateEditDto dto = UserCreateEditDto.builder()
                .username("toEntity")
                .personalInfo(PersonalInfoDto.builder()
                        .birthDate(new Birthday(LocalDate.of(2000, 1, 1)))
                        .firstname("faast")
                        .lastname("is fk boy")
                        .build())
                .role(Role.USER)
                .companyId(companyId)
                .build();

        User entity = userMapper.toEntity(dto, companyRepository);

        assertAll(
                () -> assertEquals(dto.username(), entity.getUsername()),
                () -> assertEquals(dto.personalInfo().birthDate(), entity.getPersonalInfo().getBirthDate()),
                () -> assertEquals(dto.personalInfo().firstname(), entity.getPersonalInfo().getFirstname()),
                () -> assertEquals(dto.personalInfo().lastname(), entity.getPersonalInfo().getLastname()),
                () -> assertEquals(dto.role(), entity.getRole()),
                () -> assertEquals(companyRepository.findById(companyId).get(), entity.getCompany())
        );
    }

    // refactor: divide wrongDto and dto assert
    @Test
    public void updateEntityTest() {
        UserCreateEditDto dto = UserCreateEditDto.builder()
                .username("clang")
                .personalInfo(PersonalInfoDto.builder()
                        .birthDate(new Birthday(LocalDate.now()))
                        .firstname("hai yoooo")
                        .lastname("tyytytyyt")
                        .build())
                .role(Role.ADMIN)
                .companyId(2)
                .build();

        UserCreateEditDto wrongDto = UserCreateEditDto.builder()
                .username(dto.username())
                .personalInfo(dto.personalInfo())
                .role(dto.role())
                .companyId(999)
                .build();

        User entity = User.builder()
                .username("chin")
                .personalInfo(PersonalInfo.builder()
                        .birthDate(new Birthday(LocalDate.of(2008,8,8)))
                        .firstname("chan")
                        .lastname("chon")
                        .build())
                .role(Role.USER)
                .company(companyRepository.findById(1).get())
                .build();

        userMapper.updateEntity(dto, entity, companyRepository);

        assertAll(
                () -> assertEquals(dto.username(), entity.getUsername()),
                () -> assertEquals(dto.personalInfo().birthDate(), entity.getPersonalInfo().getBirthDate()),
                () -> assertEquals(dto.personalInfo().firstname(), entity.getPersonalInfo().getFirstname()),
                () -> assertEquals(dto.personalInfo().lastname(), entity.getPersonalInfo().getLastname()),
                () -> assertEquals(dto.role(), entity.getRole()),
                () -> assertEquals(companyRepository.findById(2).get(), entity.getCompany()),
                () -> assertThrows(NoSuchElementException.class,
                        () -> userMapper.updateEntity(wrongDto, entity, companyRepository))
        );
    }

    @Test
    public void toReadDtoTest() {
        User entity = User.builder()
                .id(1L)
                .username("bob")
                .personalInfo(PersonalInfo.builder()
                        .birthDate(new Birthday(LocalDate.of(2001, 1, 1)))
                        .firstname("firsty")
                        .lastname("lasty")
                        .build())
                .role(Role.ADMIN)
                .company(Company.builder()
                        .id(2)
                        .name("Hillo")
                        .build())
                .build();

        UserReadDto dto = userMapper.toReadDto(entity);

        assertAll(
                () -> assertNotNull(dto),
                () -> assertEquals(entity.getId(), dto.id()),
                () -> assertEquals(entity.getUsername(), dto.username()),
                () -> assertEquals(entity.getPersonalInfo().getBirthDate(), dto.personalInfo().birthDate()),
                () -> assertEquals(entity.getPersonalInfo().getFirstname(), dto.personalInfo().firstname()),
                () -> assertEquals(entity.getPersonalInfo().getLastname(), dto.personalInfo().lastname()),
                () -> assertEquals(entity.getRole(), dto.role()),
                () -> assertEquals(entity.getCompany().getId(), dto.company().id()),
                () -> assertEquals(entity.getCompany().getName(), dto.company().name())
        );
    }
}

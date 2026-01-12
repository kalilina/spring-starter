package com.github.kalilina.integration.service;

import com.github.kalilina.integration.annotation.IT;
import com.github.kalilina.spring.database.entity.Birthday;
import com.github.kalilina.spring.database.entity.Role;
import com.github.kalilina.spring.database.repository.UserRepository;
import com.github.kalilina.spring.dto.PersonalInfoDto;
import com.github.kalilina.spring.dto.UserCreateEditDto;
import com.github.kalilina.spring.dto.UserReadDto;
import com.github.kalilina.spring.mapper.UserMapper;
import com.github.kalilina.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.TestConstructor.AutowireMode.ALL;

@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = ALL)
public class UserServiceIT {

    private static final Long USER_ID_1 = 1L;
    private static final Integer COMPANY_ID_1 = 1;
    private static final Integer COMPANY_ID_2 = 2;
    private final UserService userService;

    @Test
    public void findAll() {
        List<UserReadDto> userDtos = userService.findAll();
        assertThat(userDtos).hasSize(5);
    }

    @Test
    public void findById() {
        Optional<UserReadDto> maybeDto = userService.findById(USER_ID_1);
        assertTrue(maybeDto.isPresent());
        var dto = maybeDto.get();
        assertEquals("ivan@gmail.com", dto.username());
    }

    @Test
    public void save() {
        UserCreateEditDto createDto = UserCreateEditDto.builder()
                .username("back")
                .personalInfo(PersonalInfoDto.builder()
                        .birthDate(new Birthday(LocalDate.now()))
                        .firstname("end")
                        .lastname("dead")
                        .build())
                .image(new MockMultipartFile("test", new byte[0]))
                .role(Role.USER)
                .companyId(COMPANY_ID_1)
                .build();

        UserReadDto readDto = userService.save(createDto);

        assertAll(
                () -> assertEquals(createDto.username(), readDto.username()),
                () -> assertEquals(createDto.personalInfo(), readDto.personalInfo()),
                () -> assertEquals(createDto.companyId(), readDto.company().id()),
                () -> assertSame(createDto.role(), readDto.role())
        );
    }

    @Test
    public void update() {
        UserCreateEditDto editDto = UserCreateEditDto.builder()
                .username("volodya")
                .personalInfo(PersonalInfoDto.builder()
                        .birthDate(new Birthday(LocalDate.now()))
                        .firstname("vovik")
                        .lastname("vovikov")
                        .build())
                .role(Role.USER)
                .companyId(COMPANY_ID_2)
                .build();

        Optional<UserReadDto> maybeReadDto = userService.update(USER_ID_1, editDto);
        assertTrue(maybeReadDto.isPresent());
        var readDto = maybeReadDto.get();
        assertAll(
                () -> assertEquals(editDto.username(), readDto.username()),
                () -> assertEquals(editDto.personalInfo(), readDto.personalInfo()),
                () -> assertEquals(editDto.companyId(), readDto.company().id()),
                () -> assertSame(editDto.role(), readDto.role())
        );
    }

    @Test
    public void delete() {
        assertFalse(userService.delete(-123L));
        assertTrue(userService.delete(USER_ID_1));
    }
}

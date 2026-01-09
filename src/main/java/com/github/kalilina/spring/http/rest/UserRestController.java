package com.github.kalilina.spring.http.rest;

import com.github.kalilina.spring.database.entity.Birthday;
import com.github.kalilina.spring.database.entity.Role;
import com.github.kalilina.spring.dto.*;
import com.github.kalilina.spring.service.CompanyService;
import com.github.kalilina.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final CompanyService companyService;

    @GetMapping
    @ResponseBody
    public PageResponse<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
        Page<UserReadDto> page = userService.findAll(filter, pageable);
        return PageResponse.of(page);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserReadDto findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto create(@Validated @RequestBody UserCreateEditDto userCreateDto) {
        /*
            из-за того, что Birthday record спринг не может создать компонент с пустыми полями и выбрасывает ошибку

            по-хорошему нужно либо перелопатить dto (не использовать Birthday в нем), либо не использовать Birthday
            вообще нигде.

            но мне было лень это делать, поэтому создаем тот же dto, только с LocalDate.now()
         */
        if (userCreateDto.personalInfo() != null
            && userCreateDto.personalInfo().birthDate() == null) {
            userCreateDto = UserCreateEditDto.builder()
                    .username(userCreateDto.username())
                    .personalInfo(PersonalInfoDto.builder()
                            .firstname(userCreateDto.personalInfo().firstname())
                            .lastname(userCreateDto.personalInfo().lastname())
                            .birthDate(new Birthday(LocalDate.now())).build())
                    .role(userCreateDto.role())
                    .companyId(userCreateDto.companyId())
                    .build();
        }
        return userService.save(userCreateDto);
    }

    @PutMapping("/{id}")
    public UserReadDto update(@PathVariable("id") Long id,
                         @Validated UserCreateEditDto userEditDto) {
        return userService.update(id, userEditDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

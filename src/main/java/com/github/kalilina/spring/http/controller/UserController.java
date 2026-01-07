package com.github.kalilina.spring.http.controller;

import com.github.kalilina.spring.database.entity.Birthday;
import com.github.kalilina.spring.database.entity.Role;
import com.github.kalilina.spring.dto.*;
import com.github.kalilina.spring.service.CompanyService;
import com.github.kalilina.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CompanyService companyService;

    @GetMapping("/registration")
    public String registration(Model model) {
        var userCreateDto = UserCreateEditDto.builder()
                .personalInfo(PersonalInfoDto.builder().build())
                .build();
        model.addAttribute("user", userCreateDto);
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyService.findAll());
        return "user/registration";
    }

    @GetMapping
    public String findAll(Model model, UserFilter filter, Pageable pageable) {
        Page<UserReadDto> page = userService.findAll(filter, pageable);
        model.addAttribute("users", PageResponse.of(page));
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(Model model,
                           @PathVariable("id") Long id) {
        return userService.findById(id)
                .map(userReadDto -> {
                    model.addAttribute("user", userReadDto);
                    model.addAttribute("roles", Role.values());
                    model.addAttribute("companies", companyService.findAll());
                    return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute @Validated UserCreateEditDto userCreateDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        System.out.println(userCreateDto);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", userCreateDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/users/registration";
        }

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

        UserReadDto dto = userService.save(userCreateDto);
        return "redirect:/users/" + dto.id();
    }

    //    @PutMapping("/{id}")
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute @Validated UserCreateEditDto userEditDto) {
        return userService.update(id, userEditDto)
                .map(it -> "redirect:/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //    @DeleteMapping("/{id}")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/users";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception) {
        log.error("Failed to return response", exception);
        return "error/error500";
    }
}

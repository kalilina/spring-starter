package com.github.kalilina.spring.http.controller;

import com.github.kalilina.spring.database.entity.Role;
import com.github.kalilina.spring.dto.*;
import com.github.kalilina.spring.service.CompanyService;
import com.github.kalilina.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String create(@ModelAttribute UserCreateEditDto userCreateDto,
                         RedirectAttributes redirectAttributes) {
        if (true) { // заглушка. Тут будет проверка валидатором
            redirectAttributes.addFlashAttribute("user", userCreateDto);
            return "redirect:/users/registration";
        }
        UserReadDto dto = userService.save(userCreateDto);
        return "redirect:/users/" + dto.id();
    }

    //    @PutMapping("/{id}")
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute UserCreateEditDto userEditDto) {
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
}

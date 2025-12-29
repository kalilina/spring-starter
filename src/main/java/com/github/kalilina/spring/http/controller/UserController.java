package com.github.kalilina.spring.http.controller;

import com.github.kalilina.spring.dto.UserCreateEditDto;
import com.github.kalilina.spring.dto.UserReadDto;
import com.github.kalilina.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String findAll(Model model) {
//        [refactor] findAll() with a filter is more commonly used
//        model.addAttribute("users", userService.findAll(filter));
        model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(Model model,
                           @PathVariable("id") Long id) {
        return userService.findById(id)
                .map(userReadDto -> {
                    model.addAttribute("user", userReadDto);
                    return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute UserCreateEditDto userCreateDto) {
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

package com.github.kalilina.spring.http.controller;

import com.github.kalilina.spring.database.entity.Role;
import com.github.kalilina.spring.database.repository.CompanyRepository;
import com.github.kalilina.spring.dto.UserReadDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @ModelAttribute("roles")
    public List<Role> getRoles() {
        return Arrays.asList(Role.values());
    }

    @GetMapping(value = "/hello/{id}")
    public String hello(Model model,
                        @RequestHeader String accept,
                        @CookieValue("JSESSIONID") String jsessionId,
                        @PathVariable Integer id,
                        UserReadDto userReadDto) {
        System.out.println("UserReadDto.id: " + userReadDto.id());
        System.out.println("UserReadDto.username: " + userReadDto.username());
        System.out.println("header 'accept': " + accept);
        System.out.println("JSESSIONID: " + jsessionId);
        System.out.println("id: " + id);

        model.addAttribute("user", userReadDto);
        return "greeting/hello";
    }

    @GetMapping(value = "bye")
    public String bye(@SessionAttribute UserReadDto user) {
        return "greeting/bye";
    }
}

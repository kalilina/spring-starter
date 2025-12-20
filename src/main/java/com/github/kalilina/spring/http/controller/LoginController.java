package com.github.kalilina.spring.http.controller;

import com.github.kalilina.spring.dto.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(Model model,
                        @ModelAttribute("login") LoginDto loginDto) {
        System.out.println(loginDto.username());
        System.out.println(loginDto.password());
        return "redirect:/api/v1/hello/1";
    }
}

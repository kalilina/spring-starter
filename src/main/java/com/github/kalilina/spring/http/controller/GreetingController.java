package com.github.kalilina.spring.http.controller;

import com.github.kalilina.spring.database.repository.CompanyRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {

    @GetMapping(value = "/hello/{id}")
    public ModelAndView hello(ModelAndView mv, CompanyRepository companyRepository,
                              HttpServletRequest req,
                              @RequestParam(required = false) Integer age,
                              @RequestHeader String accept,
                              @CookieValue("JSESSIONID") String jsessionId,
                              @PathVariable Integer id) {
        System.out.println("age: " + age);
        System.out.println("header 'accept': " + accept);
        System.out.println("JSESSIONID: " + jsessionId);
        System.out.println("id: " + id);

        mv.setViewName("greeting/hello");
        return mv;
    }

    @GetMapping(value = "bye")
    public ModelAndView bye(ModelAndView mv) {
        mv.setViewName("greeting/bye");
        return mv;
    }
}

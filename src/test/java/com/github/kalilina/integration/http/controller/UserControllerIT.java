package com.github.kalilina.integration.http.controller;

import com.github.kalilina.integration.annotation.IT;
import com.github.kalilina.spring.database.entity.Role;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.kalilina.spring.database.entity.Birthday.Fields.birthday;
import static com.github.kalilina.spring.dto.PersonalInfoDto.Fields.*;
import static com.github.kalilina.spring.dto.UserCreateEditDto.Fields.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IT
@AutoConfigureMockMvc
@RequiredArgsConstructor
@WithMockUser(username = "test@gmail.com", password = "test", authorities = {"USER", "ADMIN"})
public class UserControllerIT {

    private final MockMvc mockMvc;

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpectAll(
                        status().is2xxSuccessful(),
                        view().name("user/users"),
                        model().attributeExists("users")
                );
    }

    @Test
    public void findById() throws Exception {
        mockMvc.perform(get("/users/{id}", 1))
                .andExpectAll(
                        status().is2xxSuccessful(),
                        view().name("user/user"),
                        model().attributeExists("user"),
                        model().attribute("user", Matchers.notNullValue())
                );
    }

    @Test
    public void save() throws Exception {
        mockMvc.perform(post("/users")
                .param(username, "rand_usr")
                .param(personalInfo + "." + firstname, "roma")
                .param(personalInfo + "." + lastname, "romovich")
                .param(personalInfo + "." + birthDate + "." + birthday, "2012-12-12")
                .param(role, Role.USER.toString())
                .param(companyId, "1")
                .with(csrf())
        ).andExpectAll(
                status().is3xxRedirection(),
                redirectedUrlPattern("/users/{\\d+}")
        );
    }

    @Test
    public void update() throws Exception {

        mockMvc.perform(post("/users/{id}/update", 1)
                .param(username, "update@user.name")
                .param(personalInfo + "." + firstname, "updatedFristy")
                .param(personalInfo + "." + lastname, "updatedLasty")
                .param(personalInfo + "." + birthDate + "." + birthday, "1999-02-03")
                .param(role, Role.USER.toString())
                .param(companyId, "2")
                .with(csrf())
        ).andExpectAll(
                status().is3xxRedirection(),
                redirectedUrl("/users/1")
        );
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(post("/users/{id}/delete", 1)
                .with(csrf())
        ).andExpectAll(
                status().is3xxRedirection(),
                redirectedUrl("/users")
        );
    }
}

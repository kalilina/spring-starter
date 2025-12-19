package com.github.kalilina.integration.repository;

import com.github.kalilina.integration.annotation.IT;
import com.github.kalilina.spring.database.entity.Role;
import com.github.kalilina.spring.database.entity.User;
import com.github.kalilina.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
public class UserRepositoryIT {

    private final UserRepository userRepository;

    @Test
    public void findAllByFirstnameAndLastnameContaining() {
        List<User> users = userRepository.findAllByPersonalInfo_FirstnameContainingAndPersonalInfo_LastnameContaining(
                "a", "a");
        assertFalse(users.isEmpty());
        users.forEach(System.out::println);
    }

    @Test
    public void findByUsername() {
        var maybeUser = userRepository.findByUsername("ivan@gmail.com");
        assertTrue(maybeUser.isPresent());
        System.out.println(maybeUser.get());
    }

    @Test
    public void updateRole() {
        List<Long> ids = List.of(2L, 3L, 4L);

        // check roles before update
        for(int i = 0; i < ids.size(); i++) {
            var maybeUser = userRepository.findById(ids.get(i));
            assertTrue(maybeUser.isPresent());
            assertEquals(Role.USER, maybeUser.get().getRole());
        }

        // update roles
        int updatedUsers = userRepository.updateRole(Role.ADMIN, ids.get(0), ids.get(1), ids.get(2));
        assertEquals(3, updatedUsers);

        // check roles after update
        for(int i = 0; i < ids.size(); i++) {
            var maybeUser = userRepository.findById(ids.get(i));
            assertTrue(maybeUser.isPresent());
            assertEquals(Role.ADMIN, maybeUser.get().getRole());
        }
    }

    @Test
    public void checkProjectionPersonalInfoDto() {
        var uniqueInfoDtos = userRepository.findAllByCompanyId(2);
        assertFalse(uniqueInfoDtos.isEmpty());
        uniqueInfoDtos.forEach(System.out::println);
        System.out.println(uniqueInfoDtos.get(0).getClass());
    }

    @Test
    public void findFirst3ByCompanyIdIsNotNullOrderByIdDesc() {
        List<User> users = userRepository.findFirst3ByCompanyIdIsNotNullOrderByIdDesc();
        assertFalse(users.isEmpty());
        assertThat(users).hasSize(3);
        users.forEach(System.out::println);
    }

    @Test
    public void findFirst3By() {
        List<User> users = userRepository.findFirst3By(Sort.by("id").descending());
        assertFalse(users.isEmpty());
        assertThat(users).hasSize(3);
        users.forEach(System.out::println);
    }

    @Test
    public void findAllBy() {
        Pageable pageable = PageRequest.of(1, 2, Sort.by("id").descending());
        Page<User> usersPage = userRepository.findAllBy(pageable); // Page extends Slice
        usersPage.forEach(System.out::println);

        System.out.println("Total pages: " + usersPage.getTotalPages());
        while(usersPage.hasNext()) {
            usersPage = userRepository.findAllBy(usersPage.nextPageable());
            usersPage.forEach(System.out::println);
        }
    }
}

package com.github.kalilina.spring.database.repository;

import com.github.kalilina.spring.database.entity.User;
import com.github.kalilina.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilterWithCompany(UserFilter filter);
}

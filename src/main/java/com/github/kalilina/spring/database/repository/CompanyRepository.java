package com.github.kalilina.spring.database.repository;

import com.github.kalilina.spring.database.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CompanyRepository {

    public Optional<Company> findById(int id) {
        System.out.println("CompanyRepository: findById method was called with argument: " + id);
        return Optional.of(new Company());
    }
}

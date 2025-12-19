package com.github.kalilina.integration.repository;

import com.github.kalilina.integration.annotation.IT;
import com.github.kalilina.spring.database.entity.Company;
import com.github.kalilina.spring.database.repository.CompanyRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
public class CompanyRepositoryIT {

    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void findById() {
        var company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }

    @Test
    public void save() {
        var company = Company.builder()
                .name("Apply")
                .locales(Map.of(
                        "en", "Apply description",
                        "ru", "Apply описание"
                ))
                .build();
        entityManager.persist(company);
    }

    // test JpaRepository
    @Test
    public void delete() {
        var maybeCompany = companyRepository.findById(1);
        assertTrue(maybeCompany.isPresent());

        companyRepository.delete(maybeCompany.get());
        companyRepository.flush();

        var maybeCompany2 = companyRepository.findById(1);
        assertTrue(maybeCompany2.isEmpty());
    }

    @Test
    public void findByName() {
        var maybeCompany = companyRepository.findByName("Google");
        assertTrue(maybeCompany.isPresent());
        System.out.println(maybeCompany.get());
        System.out.println(maybeCompany.get().getLocales());
    }

    @Test
    public void findByNameContainingIgnoreCase() {
        List<Company> companies = companyRepository.findByNameContainingIgnoreCase("A");
        assertFalse(companies.isEmpty());
        companies.forEach(System.out::println);
    }
}

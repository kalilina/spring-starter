package com.github.kalilina.spring.database.repository;

import com.github.kalilina.spring.database.entity.User;
import com.github.kalilina.spring.dto.UserFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        var cb = entityManager.getCriteriaBuilder();
        var criteria = cb.createQuery(User.class);

        var user = criteria.from(User.class);
        criteria.select(user);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.personalInfo() != null) {
            if (filter.personalInfo().firstname() != null && !filter.personalInfo().firstname().isBlank()) {
                predicates.add(cb.like(user.get("personalInfo").get("firstname"), filter.personalInfo().firstname()));
            }
            if (filter.personalInfo().lastname() != null && !filter.personalInfo().lastname().isBlank()) {
                predicates.add(cb.like(user.get("personalInfo").get("lastname"), filter.personalInfo().lastname()));
            }
            if (filter.personalInfo().birthDate().birthday() != null) {
                predicates.add(cb.lessThan(
                        user.get("personalInfo").get("birthDate").as(LocalDate.class),
                        filter.personalInfo().birthDate().birthday()));
            }
        }

        criteria.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(criteria).getResultList();
    }
}

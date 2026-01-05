package com.github.kalilina.spring.database.repository;

import com.github.kalilina.spring.database.entity.User;
import com.github.kalilina.spring.dto.QPredicate;
import com.github.kalilina.spring.dto.UserFilter;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.github.kalilina.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        Predicate predicate = filter.personalInfo() == null
                ? null
                : QPredicate.builder()
                .add(filter.personalInfo().firstname(), user.personalInfo.firstname::containsIgnoreCase)
                .add(filter.personalInfo().lastname(), user.personalInfo.lastname::containsIgnoreCase)
//                    .add(filter.personalInfo().birthDate(), user.personalInfo.birthDate::eq) // не хочу переделывать entity PersonalInfo
                                                                                               // (нужно поле Birthday заменить на LocalDate)
                .buildAnd();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();

        // using Criteria API
//        var cb = entityManager.getCriteriaBuilder();
//        var criteria = cb.createQuery(User.class);
//
//        var user = criteria.from(User.class);
//        criteria.select(user);
//
//        List<Predicate> predicates = new ArrayList<>();
//        if (filter.personalInfo() != null) {
//            if (filter.personalInfo().firstname() != null && !filter.personalInfo().firstname().isBlank()) {
//                predicates.add(cb.like(user.get("personalInfo").get("firstname"), filter.personalInfo().firstname()));
//            }
//            if (filter.personalInfo().lastname() != null && !filter.personalInfo().lastname().isBlank()) {
//                predicates.add(cb.like(user.get("personalInfo").get("lastname"), filter.personalInfo().lastname()));
//            }
//            if (filter.personalInfo().birthDate().birthday() != null) {
//                predicates.add(cb.lessThan(
//                        user.get("personalInfo").get("birthDate").as(LocalDate.class),
//                        filter.personalInfo().birthDate().birthday()));
//            }
//        }
//
//        criteria.where(predicates.toArray(Predicate[]::new));
//
//        return entityManager.createQuery(criteria).getResultList();
    }
}

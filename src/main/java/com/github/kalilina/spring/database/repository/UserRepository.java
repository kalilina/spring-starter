package com.github.kalilina.spring.database.repository;

import com.github.kalilina.spring.database.entity.Role;
import com.github.kalilina.spring.database.entity.User;
import com.github.kalilina.spring.dto.IUniqueInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>,
        FilterUserRepository, QuerydslPredicateExecutor<User> {

    @Query("""
        select u from User u
        where u.personalInfo.firstname ilike %:firstname%
          and u.personalInfo.lastname ilike %:lastname%
        """)
    List<User> findAllByPersonalInfo_FirstnameContainingAndPersonalInfo_LastnameContaining(
            String firstname, String lastname);

    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username",
            nativeQuery = true)
    Optional<User> findByUsername(String username);

    @Modifying(clearAutomatically = true)
    @Query("""
        update User u set u.role = :role
        where u.id in (:ids)
        """)
    int updateRole(Role role, Long... ids);

    @Query(value = """
        SELECT u.id, u.username FROM users u
        WHERE u.company_id = :companyId
        """,
    nativeQuery = true)
    List<IUniqueInfoDto> findAllByCompanyId(Integer companyId);
//    <T> List<T> findAllByCompanyId(Integer companyId, Class<T> clazz);

    List<User> findFirst3ByCompanyIdIsNotNullOrderByIdDesc();

    // dynamic sort
    List<User> findFirst3By(Sort sort);

    Page<User> findAllBy(Pageable pageable);

    @EntityGraph(attributePaths = "company")
    @Query("select u from User u")
    List<User> findAllWithCompany();

    @EntityGraph(attributePaths = "company")
    @Query("select u from User u where u.id = :id")
    Optional<User> findByIdWithCompany(Long id);
}


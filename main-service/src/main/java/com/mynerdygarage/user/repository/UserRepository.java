package com.mynerdygarage.user.repository;

import com.mynerdygarage.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsById(Long id);

    boolean existsByNameIgnoreCaseOrEmailIgnoreCase(String name, String email);

    @Query("""
            SELECT u
            FROM User as u
            WHERE u.name = :name
            OR u.email = :email
            ORDER BY u.id
            LIMIT 1
            """)
    Optional<User> findByNameOrEmail(String name, String email);
}

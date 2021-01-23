package com.sadheera.wileytest.repository;

import com.sadheera.wileytest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query("SELECT emailVerified FROM User WHERE email = ?1")
    Boolean findEmailVerifiedByEmail(String email);
}

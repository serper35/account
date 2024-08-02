package com.hesfintech.check.repository;

import com.hesfintech.check.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByLogin(String login);

    Optional<User> findByLogin(String login);
}

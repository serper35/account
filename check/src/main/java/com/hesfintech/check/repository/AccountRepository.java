package com.hesfintech.check.repository;

import com.hesfintech.check.model.Account;
import com.hesfintech.check.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUser(User user);
}

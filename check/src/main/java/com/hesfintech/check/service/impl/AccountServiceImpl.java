package com.hesfintech.check.service.impl;

import com.hesfintech.check.exception.AccountBlockedException;
import com.hesfintech.check.exception.AccountNotFoundException;
import com.hesfintech.check.exception.NotEnoughMoneyException;
import com.hesfintech.check.model.Account;
import com.hesfintech.check.model.User;
import com.hesfintech.check.repository.AccountRepository;
import com.hesfintech.check.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public BigDecimal getBalance(User user) {
        Account account = accountRepository.findByUser(user).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return account.getBalance();
    }

    @Override
    public Account depositMoney(User user, BigDecimal deposit) {
        Account account = accountRepository.findByUser(user).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if (account.isBlock()) {
            throw new AccountBlockedException("Account is blocked");
        }
        account.setBalance(account.getBalance().add(deposit));
        return accountRepository.save(account);
    }

    @Override
    public Account withdrawMoney(User user, BigDecimal withdraw) {
        Account account = accountRepository.findByUser(user).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if (account.isBlock()) {
            throw new AccountBlockedException("Account is blocked");
        }
        if (account.getBalance().compareTo(withdraw) < 0) {
            throw new NotEnoughMoneyException("Not enough money");
        }

        account.setBalance(account.getBalance().subtract(withdraw));
        return accountRepository.save(account);
    }

    @Override
    public void blockAccount(User user) {
        Account account = accountRepository.findByUser(user).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        account.setBlock(true);
        accountRepository.save(account);
    }

    @Override
    public void unblockAccount(User user) {
        Account account = accountRepository.findByUser(user).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        account.setBlock(false);
        accountRepository.save(account);
    }
}

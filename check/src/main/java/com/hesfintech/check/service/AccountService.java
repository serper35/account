package com.hesfintech.check.service;

import com.hesfintech.check.model.Account;
import com.hesfintech.check.model.User;

import java.math.BigDecimal;

public interface AccountService {
    BigDecimal getBalance(User user);
    Account depositMoney(User user, BigDecimal deposit);
    Account withdrawMoney(User user, BigDecimal withdraw);
    void blockAccount(User user);
    void unblockAccount(User user);
}

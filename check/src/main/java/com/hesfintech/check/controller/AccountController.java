package com.hesfintech.check.controller;

import com.hesfintech.check.model.Account;
import com.hesfintech.check.model.User;
import com.hesfintech.check.service.AccountService;
import com.hesfintech.check.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final UserService userService;

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance() {
        User currentUser = userService.getCurrentUser();
        BigDecimal balance = accountService.getBalance(currentUser);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/deposit")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Account> deposit(@RequestBody User user,
                                           @RequestParam BigDecimal deposit) {
        User currentUser = userService.getUserById(user.getId());
        Account updatedAccount = accountService.depositMoney(user, deposit);
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/withdraw")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Account> withdraw(@RequestBody User user,
                                                 @RequestParam BigDecimal withdraw) {
        User currentUser = userService.getUserById(user.getId());
        Account updatedAccount = accountService.withdrawMoney(user, withdraw);
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/block")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> block(@RequestBody User user) {
        User userToBlock = userService.getUserById(user.getId());
        accountService.blockAccount(userToBlock);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/unblock")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> unblock(@RequestBody User user) {
        User userToUnblock = userService.getUserById(user.getId());
        accountService.unblockAccount(userToUnblock);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/change-password")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> changePassword(@RequestParam String prevPass, String newPass) {
        User currentUser = userService.getCurrentUser();
        userService.changePassword(currentUser, prevPass, newPass);
        return ResponseEntity.noContent().build();
    }
}

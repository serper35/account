package com.hesfintech.check.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    private BigDecimal balance;
    private boolean block;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

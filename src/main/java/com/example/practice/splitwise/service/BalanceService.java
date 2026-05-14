package com.example.practice.splitwise.service;

import com.example.practice.splitwise.model.Expense;
import com.example.practice.splitwise.model.Split;
import com.example.practice.splitwise.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BalanceService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public BalanceService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Map<Long, BigDecimal> getBalances() {
        Map<Long, BigDecimal> balances = new HashMap<>();
        List<Expense> expenses = expenseRepository.findAll();

        for (Expense expense : expenses) {
            Long payerId = expense.getPayer().getId();
            BigDecimal totalAmount = expense.getAmount();

            // Payer is owed the full amount initially
            balances.put(payerId, balances.getOrDefault(payerId, BigDecimal.ZERO).add(totalAmount));

            // Participants owe their split amount
            for (Split split : expense.getSplits()) {
                Long participantId = split.getUser().getId();
                BigDecimal splitAmount = split.getAmount();
                balances.put(participantId, balances.getOrDefault(participantId, BigDecimal.ZERO).subtract(splitAmount));
            }
        }

        return balances;
    }
}

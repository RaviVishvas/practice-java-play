package com.example.practice.splitwise.service;

import com.example.practice.splitwise.model.Expense;
import com.example.practice.splitwise.model.Split;
import com.example.practice.splitwise.model.User;
import com.example.practice.splitwise.repository.ExpenseRepository;
import com.example.practice.splitwise.service.split.ExpenseSplitStrategy;
import com.example.practice.splitwise.service.split.ExpenseSplitStrategyFactory;
import com.example.practice.splitwise.service.split.SplitType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;
    private final ExpenseSplitStrategyFactory splitStrategyFactory;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, UserService userService, ExpenseSplitStrategyFactory splitStrategyFactory) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
        this.splitStrategyFactory = splitStrategyFactory;
    }

    @Transactional
    public Expense createExpense(String description, BigDecimal amount, Long paidByUserId, List<Long> participantIds, SplitType splitType, List<BigDecimal> splitValues) {
        User paidBy = userService.getUserById(paidByUserId);

        List<User> participants = participantIds.stream()
                .map(userService::getUserById)
                .collect(Collectors.toList());

        ExpenseSplitStrategy strategy = splitStrategyFactory.getStrategy(splitType);
        List<Split> splits = strategy.calculateSplits(amount, paidBy, participants, splitValues);

        Expense expense = new Expense();
        expense.setDescription(description);
        expense.setAmount(amount);
        expense.setPayer(paidBy);
        expense.setSplits(splits);

        for (Split split : splits) {
            split.setExpense(expense);
        }

        return expenseRepository.save(expense);
    }
}

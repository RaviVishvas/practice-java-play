package com.example.practice.splitwise.controller;

import com.example.practice.splitwise.controller.dto.CreateExpenseRequest;
import com.example.practice.splitwise.model.Expense;
import com.example.practice.splitwise.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<?> createExpense(@RequestBody CreateExpenseRequest request) {
        try {
            Expense expense = expenseService.createExpense(
                    request.getDescription(),
                    request.getAmount(),
                    request.getPaidByUserId(),
                    request.getParticipantIds(),
                    request.getSplitType(),
                    request.getSplitValues()
            );
            return ResponseEntity.ok(expense);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

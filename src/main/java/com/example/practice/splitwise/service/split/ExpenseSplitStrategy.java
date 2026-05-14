package com.example.practice.splitwise.service.split;

import com.example.practice.splitwise.model.Split;
import com.example.practice.splitwise.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseSplitStrategy {
    List<Split> calculateSplits(BigDecimal totalAmount, User paidBy, List<User> participants, List<BigDecimal> splitValues);
}

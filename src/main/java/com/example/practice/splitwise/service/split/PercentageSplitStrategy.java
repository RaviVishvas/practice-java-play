package com.example.practice.splitwise.service.split;

import com.example.practice.splitwise.model.Split;
import com.example.practice.splitwise.model.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PercentageSplitStrategy implements ExpenseSplitStrategy {
    @Override
    public List<Split> calculateSplits(BigDecimal totalAmount, User paidBy, List<User> participants, List<BigDecimal> splitValues) {
        if (participants.size() != splitValues.size()) {
            throw new IllegalArgumentException("Number of participants and split percentages must be the same.");
        }

        BigDecimal totalPercentage = splitValues.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        if (totalPercentage.compareTo(new BigDecimal("100")) != 0) {
            throw new IllegalArgumentException("Sum of split percentages must be equal to 100.");
        }

        List<Split> splits = new ArrayList<>();
        BigDecimal hundred = new BigDecimal("100");
        for (int i = 0; i < participants.size(); i++) {
            User participant = participants.get(i);
            BigDecimal percentage = splitValues.get(i);
            BigDecimal amount = totalAmount.multiply(percentage).divide(hundred, 2, RoundingMode.HALF_UP);

            Split split = new Split();
            split.setUser(participant);
            split.setAmount(amount);
            splits.add(split);
        }
        return splits;
    }
}

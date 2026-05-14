package com.example.practice.splitwise.service.split;

import com.example.practice.splitwise.model.Split;
import com.example.practice.splitwise.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExactSplitStrategy implements ExpenseSplitStrategy {
    @Override
    public List<Split> calculateSplits(BigDecimal totalAmount, User paidBy, List<User> participants, List<BigDecimal> splitValues) {
        if (participants.size() != splitValues.size()) {
            throw new IllegalArgumentException("Number of participants and split values must be the same.");
        }

        BigDecimal sumOfSplitValues = splitValues.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        if (totalAmount.compareTo(sumOfSplitValues) != 0) {
            throw new IllegalArgumentException("Sum of split values must be equal to the total amount.");
        }

        List<Split> splits = new ArrayList<>();
        for (int i = 0; i < participants.size(); i++) {
            User participant = participants.get(i);
            BigDecimal amount = splitValues.get(i);

            Split split = new Split();
            split.setUser(participant);
            split.setAmount(amount);
            splits.add(split);
        }
        return splits;
    }
}

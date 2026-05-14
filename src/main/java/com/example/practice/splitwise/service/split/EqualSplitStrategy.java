package com.example.practice.splitwise.service.split;

import com.example.practice.splitwise.model.Split;
import com.example.practice.splitwise.model.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class EqualSplitStrategy implements ExpenseSplitStrategy {
    @Override
    public List<Split> calculateSplits(BigDecimal totalAmount, User paidBy, List<User> participants, List<BigDecimal> splitValues) {
        List<Split> splits = new ArrayList<>();
        int numberOfParticipants = participants.size();
        BigDecimal amountPerParticipant = totalAmount.divide(BigDecimal.valueOf(numberOfParticipants), 2, RoundingMode.HALF_UP);

        for (User participant : participants) {
            Split split = new Split();
            split.setUser(participant);
            split.setAmount(amountPerParticipant);
            splits.add(split);
        }
        return splits;
    }
}

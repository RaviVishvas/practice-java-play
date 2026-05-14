package com.example.practice.splitwise.service.split;

import org.springframework.stereotype.Component;

@Component
public class ExpenseSplitStrategyFactory {

    public ExpenseSplitStrategy getStrategy(SplitType splitType) {
        switch (splitType) {
            case EQUAL:
                return new EqualSplitStrategy();
            case EXACT:
                return new ExactSplitStrategy();
            case PERCENTAGE:
                return new PercentageSplitStrategy();
            default:
                throw new IllegalArgumentException("Invalid split type: " + splitType);
        }
    }
}

package com.example.practice.splitwise.controller.dto;

import com.example.practice.splitwise.service.split.SplitType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateExpenseRequest {
    private String description;
    private BigDecimal amount;
    private Long paidByUserId;
    private List<Long> participantIds;
    private SplitType splitType;
    private List<BigDecimal> splitValues;
}

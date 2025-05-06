package com.shivam.ExpenseTracker.dto;

import com.shivam.ExpenseTracker.model.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseResponse {
    private Long id;
    private String title;
    private BigDecimal amount;
    private Category category;
    private LocalDate date;

}

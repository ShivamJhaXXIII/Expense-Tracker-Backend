package com.shivam.ExpenseTracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Expense {
    public Expense(Long id, String title, String category, BigDecimal amount, LocalDate date) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public Expense() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Title is required") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is required") String title) {
        this.title = title;
    }

    public @NotNull(message = "Amount is Required") @Positive(message = "Amount must be Positive") BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(@NotNull(message = "Amount is Required") @Positive(message = "Amount must be Positive") BigDecimal amount) {
        this.amount = amount;
    }

    public @NotBlank(message = "Category is required") String getCategory() {
        return category;
    }

    public void setCategory(@NotBlank(message = "Category is required") String category) {
        this.category = category;
    }

    public @NotNull(message = "Date is required") LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull(message = "Date is required") LocalDate date) {
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Amount is Required")
    @Positive(message = "Amount must be Positive")
    private BigDecimal amount;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Date is required")
    private LocalDate date;
}

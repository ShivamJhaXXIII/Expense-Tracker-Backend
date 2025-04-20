package com.shivam.ExpenseTracker.repository;

import com.shivam.ExpenseTracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}

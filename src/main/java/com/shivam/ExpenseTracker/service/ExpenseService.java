package com.shivam.ExpenseTracker.service;

import com.shivam.ExpenseTracker.model.Expense;

import java.util.List;

public interface ExpenseService {
    public List<Expense> getAllExpenses();
    public Expense getExpenseById(Long id);
    public Expense saveExpense(Expense expense);
    public Expense updateExpense(Long id, Expense updatedExpense);
    public void deleteExpense(Long id);
}

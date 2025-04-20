package com.shivam.ExpenseTracker.service;

import com.shivam.ExpenseTracker.model.Expense;
import com.shivam.ExpenseTracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found for id: " + id));

        return expense;
    }

    @Override
    public Expense saveExpense(Expense expense) {
        Expense savedExpense = expenseRepository.save(expense);
        return savedExpense;
    }

    @Override
    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found for id: " + id));  // Handle not found case

        existingExpense.setTitle(updatedExpense.getTitle());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setCategory(updatedExpense.getCategory());
        existingExpense.setDate(updatedExpense.getDate());

        return expenseRepository.save(existingExpense);
    }


    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}

package com.shivam.ExpenseTracker.service;

import com.shivam.ExpenseTracker.dto.ExpenseRequest;
import com.shivam.ExpenseTracker.dto.ExpenseResponse;
import com.shivam.ExpenseTracker.excpeption.ResourceNotFoundException;
import com.shivam.ExpenseTracker.model.Expense;
import com.shivam.ExpenseTracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{

    private static final Logger logger = LoggerFactory.getLogger(ExpenseServiceImpl.class);
    private final ExpenseRepository expenseRepository;

    @Override
    public List<Expense> getAllExpenses() {
        logger.info("Fetching all Expenses");
        return expenseRepository.findAll();
    }

    @Override
    public Expense getExpenseById(Long id) {
        logger.info("Fetching expense with id: {}", id);
        return expenseRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Expense not found for id: {}", id);
                    return new ResourceNotFoundException("Expense not found for id: " + id);
                });
    }

    @Override
    public Expense saveExpense(Expense expense) {
        logger.info("Saving new expense: {}", expense.getTitle());
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpense(Long id, Expense updatedExpense) {
        logger.info("Updating expense with id: {}", id);
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Expense not found for update with id: {}", id);
                    return new ResourceNotFoundException("Expense not found for id: " + id);
                });  // Handle not found case

        existingExpense.setTitle(updatedExpense.getTitle());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setCategory(updatedExpense.getCategory());
        existingExpense.setDate(updatedExpense.getDate());

        return expenseRepository.save(existingExpense);
    }


    @Override
    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            logger.error("Expense not found for deletion with id: {}", id);
            throw new ResourceNotFoundException("Expense not found");
        }
        expenseRepository.deleteById(id);
        logger.info("Deleted expense with id: {}", id);
    }
}

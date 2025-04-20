package com.shivam.ExpenseTracker.controller;

import com.shivam.ExpenseTracker.model.Expense;
import com.shivam.ExpenseTracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> list = expenseService.getAllExpenses();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @PostMapping
    public ResponseEntity<Expense> addExpense(@Valid @RequestBody Expense expense) {
        Expense saved = expenseService.saveExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        Expense updatedExpense = expenseService.updateExpense(id, expense);
        return ResponseEntity.status(HttpStatus.OK).body(updatedExpense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
    }
}

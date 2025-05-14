package com.shivam.ExpenseTracker.controller;

import com.shivam.ExpenseTracker.dto.ExpenseRequest;
import com.shivam.ExpenseTracker.dto.ExpenseResponse;
import com.shivam.ExpenseTracker.model.Expense;
import com.shivam.ExpenseTracker.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    // The ExpenseController class handles HTTP requests related to expenses.
    // It provides endpoints for CRUD operations on expenses.
    // The controller uses the ExpenseService to perform the actual operations.

    // The @CrossOrigin annotation allows cross-origin requests from the specified origin.
    // The @RestController annotation indicates that this class is a RESTful controller.
    // The @RequestMapping annotation specifies the base URL for all endpoints in this controller.
    // The @RequiredArgsConstructor annotation generates a constructor with required arguments (final fields).

    private final ExpenseService expenseService;


    private Expense toEntity(ExpenseRequest dto) {
        Expense expense = new Expense();
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());
        expense.setDate(dto.getDate());
        return expense;
    }

    private ExpenseResponse toResponse(Expense expense) {
        ExpenseResponse dto = new ExpenseResponse();
        dto.setId(expense.getId());
        dto.setTitle(expense.getTitle());
        dto.setAmount(expense.getAmount());
        dto.setCategory(expense.getCategory());
        dto.setDate(expense.getDate());
        return dto;
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getAllExpenses() {
        List<Expense> list = expenseService.getAllExpenses();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ExpenseResponse> responseList = list.stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(toResponse(expenseService.getExpenseById(id)));
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> addExpense(@Valid @RequestBody ExpenseRequest expense) {
        Expense saved = expenseService.saveExpense(toEntity(expense));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> updateExpense(@PathVariable Long id,@Valid @RequestBody ExpenseRequest expense) {
        Expense updatedExpense = expenseService.updateExpense(id, toEntity(expense));
        return ResponseEntity.status(HttpStatus.OK).body(toResponse(updatedExpense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}

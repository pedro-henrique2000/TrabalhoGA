package com.project.expenses.service;

import com.project.expenses.domain.Category;
import com.project.expenses.domain.Expense;
import com.project.expenses.domain.User;
import com.project.expenses.dto.ExpenseDTO;
import com.project.expenses.repository.JpaExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private final JpaExpenseRepository repository;
    private final UserService userService;
    private final CategoryService categoryService;

    public ExpenseService(JpaExpenseRepository repository, UserService userService, CategoryService categoryService) {
        this.repository = repository;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public Long save(ExpenseDTO expenseDTO) {
        Expense expense = Expense.ExpenseBuilder.anExpense().withAmount(expenseDTO.getAmount())
                .withDescription(expenseDTO.getDescription())
                .build();
        User user = getUser(expenseDTO);
        user.addExpense(expense);
        for (Long id : expenseDTO.getCategoryIds()) {
            Category category = getCategoryById(id);
            category.addExpense(expense);
        }
        Expense saved = repository.save(expense);
        return saved.getId();
    }

    private Category getCategoryById(Long id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            throw new RuntimeException("invalid category id");
        }
        return category;
    }

    private User getUser(ExpenseDTO expenseDTO) {
        User user = userService.findById(expenseDTO.getUserId());
        if (user == null) {
            throw new RuntimeException("invalid user id");
        }
        return user;
    }

}

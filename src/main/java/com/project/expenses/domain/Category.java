package com.project.expenses.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TB_CATEGORIES")
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // RELACAO N:N
    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "categories")
    private Set<Expense> expenses = new HashSet<>();

    public Category() {

    }

    public void addExpense(Expense expense) {
        if (expenses == null) {
            expenses = new HashSet<>();
        }
        expenses.add(expense);
        expense.getCategories().add(this);
    }

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

}

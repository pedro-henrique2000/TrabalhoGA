package com.project.expenses.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TB_EXPENSE")
public class Expense {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne()
    // RELACAO 1:N
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // RELACAO N:N
    @ManyToMany
    @JoinTable(
            name="expense_categories",
            joinColumns={@JoinColumn(name="expense_id")},
            inverseJoinColumns={@JoinColumn(name="category_id")}
    )
    private Set<Category> categories = new HashSet<>();

    public Set<Category> getCategories() {
        if (categories == null) {
            categories = new HashSet<>();
        }
        return categories;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static final class ExpenseBuilder {
        private String description;
        private BigDecimal amount;

        private ExpenseBuilder() {
        }

        public static ExpenseBuilder anExpense() {
            return new ExpenseBuilder();
        }

        public ExpenseBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ExpenseBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Expense build() {
            Expense expense = new Expense();
            expense.amount = this.amount;
            expense.description = this.description;
            return expense;
        }
    }
}

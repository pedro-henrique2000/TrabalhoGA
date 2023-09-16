package com.project.expenses.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TB_USERS")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // RELACAO 1:1
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "expense_preference_id")
    private UserExpensePreference userExpensePreference;

    // RELACAO 1:N
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<Expense> expenses= new HashSet<>();

    public void setUserExpensePreference(UserExpensePreference userExpensePreference) {
        this.userExpensePreference = userExpensePreference;
        userExpensePreference.setUser(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void addExpense(Expense expense) {
        if (expenses == null) {
            expenses = new HashSet<>();
        }
        expenses.add(expense);
        expense.setUser(this);
    }

    public static final class UserBuilder {
        private String name;
        private LocalDate birthDate;
        private UserExpensePreference userExpensePreference;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public UserBuilder withUserPreference(UserExpensePreference obj) {
            this.userExpensePreference = obj;
            return this;
        }

        public User build() {
            User user = new User();
            user.name = this.name;
            user.birthDate = this.birthDate;
            user.userExpensePreference = this.userExpensePreference;
            return user;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

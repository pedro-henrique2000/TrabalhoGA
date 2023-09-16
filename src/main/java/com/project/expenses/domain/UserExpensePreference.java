package com.project.expenses.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_USER_EXPENCE_PREFERENCE")
public class UserExpensePreference {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @Column(nullable = false)
    private BigDecimal amount;

    @OneToOne(mappedBy = "userExpensePreference")
    // RELACAO 1:1
    private User user;

    private boolean isActive;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserExpensePreference that = (UserExpensePreference) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static final class UserExpensePreferenceBuilder {
        private String description;
        private BigDecimal amount;
        private boolean isActive;

        private UserExpensePreferenceBuilder() {
        }

        public static UserExpensePreferenceBuilder anUserExpensePreference() {
            return new UserExpensePreferenceBuilder();
        }

        public UserExpensePreferenceBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public UserExpensePreferenceBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public UserExpensePreferenceBuilder withIsActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public UserExpensePreference build() {
            UserExpensePreference userExpensePreference = new UserExpensePreference();
            userExpensePreference.setDescription(description);
            userExpensePreference.setAmount(amount);
            userExpensePreference.isActive = this.isActive;
            return userExpensePreference;
        }
    }
}

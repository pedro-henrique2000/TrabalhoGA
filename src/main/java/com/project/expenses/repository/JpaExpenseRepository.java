package com.project.expenses.repository;

import com.project.expenses.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaExpenseRepository extends JpaRepository<Expense, Long> {
}

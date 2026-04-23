package com.Uday.BudgetDekho.Repo;

import com.Uday.BudgetDekho.Model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepo extends JpaRepository<Budget,Long> {
}

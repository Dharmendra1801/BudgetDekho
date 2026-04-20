package com.Uday.BudgetDekho.Repo;

import com.Uday.BudgetDekho.Model.TimeDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeDateRepo extends JpaRepository<TimeDate,Long> {
}

package com.Uday.BudgetDekho.Repo;

import com.Uday.BudgetDekho.Model.Amount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmountRepo extends JpaRepository<Amount,String> {
}

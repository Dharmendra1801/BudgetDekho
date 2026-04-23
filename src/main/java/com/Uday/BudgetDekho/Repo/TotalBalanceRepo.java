package com.Uday.BudgetDekho.Repo;

import com.Uday.BudgetDekho.Model.TotalBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalBalanceRepo extends JpaRepository<TotalBalance,Double> {
}

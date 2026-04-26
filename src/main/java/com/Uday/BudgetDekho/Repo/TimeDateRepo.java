package com.Uday.BudgetDekho.Repo;

import com.Uday.BudgetDekho.Model.TimeDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeDateRepo extends JpaRepository<TimeDate,String> {
    @Modifying
    @Query("delete from TimeDate")
    void deleteAll();
}

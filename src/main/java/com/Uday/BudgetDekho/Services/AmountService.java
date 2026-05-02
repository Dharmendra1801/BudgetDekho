package com.Uday.BudgetDekho.Services;

import com.Uday.BudgetDekho.Model.Amount;
import com.Uday.BudgetDekho.Repo.AmountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmountService {

    @Autowired
    AmountRepo amountRepo;

    public Long getCurrentAmount() {
        Amount amount = amountRepo.findById("current").orElse(null);
        return amount==null? null: amount.getAmount();
    }
}

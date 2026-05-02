package com.Uday.BudgetDekho.Controllers;

import com.Uday.BudgetDekho.Model.Transaction;
import com.Uday.BudgetDekho.Repo.TransactionRepo;
import com.Uday.BudgetDekho.Services.BudgetService;
import com.Uday.BudgetDekho.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    BudgetService budgetService;

    @Autowired
    TransactionService transactionService;

    @GetMapping("/")
    public void refresh() throws Exception {
        System.out.println("hi");
        budgetService.refreshBudgets();
    }

    @GetMapping("/data")
    public List<Transaction> updateData() {
//        budgetService.updateData(new ArrayList<>());
        return transactionService.getAll();
    }

}

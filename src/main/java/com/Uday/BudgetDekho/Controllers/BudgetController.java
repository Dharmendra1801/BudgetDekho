package com.Uday.BudgetDekho.Controllers;

import com.Uday.BudgetDekho.Model.Transaction;
import com.Uday.BudgetDekho.Repo.TransactionRepo;
import com.Uday.BudgetDekho.Services.BudgetService;
import com.Uday.BudgetDekho.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    BudgetService budgetService;

    @Autowired
    TransactionService transactionService;

    @GetMapping("/")
    public void refresh() throws Exception {
        budgetService.refreshBudgets();
    }

    @PostMapping("/add")
    public void updateCurrentAmount(@RequestBody String amt) {
        System.out.println("HI" + amt.getClass().getName() +" yeh hai > " + amt);
        budgetService.updateCurrentAmount(amt);
    }

    @GetMapping("/amt_left")
    public ResponseEntity<String> amountLeft() {
        return ResponseEntity.ok(budgetService.getCurrentAmount());
    }
}

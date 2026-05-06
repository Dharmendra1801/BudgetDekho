package com.Uday.BudgetDekho.Controllers;

import com.Uday.BudgetDekho.Model.Transaction;
import com.Uday.BudgetDekho.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionsController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/all")
    public List<Transaction> getAll() {
        return transactionService.getAll();
    }

    @DeleteMapping("/delete")
    public void deleteTransaction(@RequestBody String x){
        transactionService.reverseTransactions(x);
    }
}

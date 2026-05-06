package com.Uday.BudgetDekho.Controllers;

import com.Uday.BudgetDekho.Model.Transaction;
import com.Uday.BudgetDekho.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/all")
    public List<Transaction> getAll() {
        return transactionService.getAll();
    }
}

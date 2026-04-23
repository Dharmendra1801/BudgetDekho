package com.Uday.BudgetDekho.Controllers;

import com.Uday.BudgetDekho.Model.TimeDate;
import com.Uday.BudgetDekho.Model.Transaction;
import com.Uday.BudgetDekho.Repo.TimeDateRepo;
import com.Uday.BudgetDekho.Services.BudgetService;
import com.Uday.BudgetDekho.Services.GmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    BudgetService budgetService;

    @Autowired
    TimeDateRepo timeDateRepo;

    @Autowired
    GmailService gmailService;

    @GetMapping("/refresh")
    public List<Transaction> test() throws Exception {
        return budgetService.refreshBudgets();
    }

}

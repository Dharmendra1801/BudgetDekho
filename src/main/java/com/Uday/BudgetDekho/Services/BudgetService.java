package com.Uday.BudgetDekho.Services;

import com.Uday.BudgetDekho.Model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    @Autowired
    GmailService gmailService;

    @Autowired
    StringManiService stringManiService;

    @Autowired
    TransactionService transactionService;

    public List<Transaction> refreshBudgets() throws Exception {
        String date = "2026/04/17";
        List<String> spentMails = gmailService.getSpentMails(date);
        List<String> earnedMails = gmailService.getEarnedMails(date);
        List<String> resultant = stringManiService.getDataFromMails(spentMails,earnedMails);
        List<Transaction> transactions = transactionService.convertToObj(resultant);
        updateData(transactions);
        return transactions;
    }

    private void updateData(List<Transaction> transactions) {
    }
}

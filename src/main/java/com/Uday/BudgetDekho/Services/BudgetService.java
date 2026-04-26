package com.Uday.BudgetDekho.Services;

import com.Uday.BudgetDekho.DTO.MailsDTO;
import com.Uday.BudgetDekho.Model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    @Autowired
    GmailService gmailService;

    @Autowired
    ManiService maniService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    TimeDateService timeDateService;

    public List<Transaction> refreshBudgets() throws Exception {
        String date = timeDateService.getDate();
        MailsDTO mailsDTO = gmailService.getMails(date);
        List<String> resultant = maniService.getDataFromMails(mailsDTO.getSpentMails(),
                                                            mailsDTO.getEarnedMails(),
                                                            mailsDTO.getSpentMailsTime(),
                                                            mailsDTO.getEarnedMailsTime());
        List<Transaction> transactions = transactionService.convertToObj(resultant);
        if (!updateData(transactions)) return null;
        return transactions;
    }

    private boolean updateData(List<Transaction> transactions) {
        return true;
    }

}

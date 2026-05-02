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

    @Autowired
    AmountService amountService;

    public void refreshBudgets() throws Exception {
        String date = timeDateService.getDate();
        MailsDTO mailsDTO = gmailService.getMails(date);
        List<String> resultant = maniService.getDataFromMails(mailsDTO.getSpentMails(),
                                                            mailsDTO.getEarnedMails(),
                                                            mailsDTO.getSpentMailsTime(),
                                                            mailsDTO.getEarnedMailsTime());
        transactionService.save(resultant);
    }

    public void updateData(List<Transaction> transactions) {
        if (amountService.getCurrentAmount()==null) {
            timeDateService.insertFirstDate();
        }
    }

}

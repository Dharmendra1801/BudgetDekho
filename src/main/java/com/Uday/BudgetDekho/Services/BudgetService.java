package com.Uday.BudgetDekho.Services;

import com.Uday.BudgetDekho.DTO.MailsDTO;
import org.jspecify.annotations.Nullable;
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
        transactionService.save(resultant,mailsDTO.getTime(),mailsDTO.getDate());
    }

    public boolean isFirstTime() {
        Boolean amt = amountService.getCurrentAmount()==null;
        Boolean time = timeDateService.getCurrentDateObj()==null;
        if (time) timeDateService.setFirstDate();
        return amt || time;
    }

    public void updateCurrentAmount(String amt) {
        amountService.updateCurrentAmount(Double.parseDouble(amt));
    }

    public String getCurrentAmount() {
        Double amt = amountService.getCurrentAmount();
        return amt.toString();
    }
}

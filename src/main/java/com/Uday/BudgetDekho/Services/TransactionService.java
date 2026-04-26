package com.Uday.BudgetDekho.Services;

import com.Uday.BudgetDekho.Model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    ManiService maniService;

    public List<Transaction> convertToObj(List<String> resultant) {
        List<Transaction> list = new ArrayList<>();
        for (String t: resultant) {
            String[] splited = t.split("_");
            Transaction transaction = new Transaction();
            transaction.setAmount(Double.parseDouble(splited[0]));
            transaction.setOtherPerson(splited[1]);
            transaction.setDate(maniService.formatDate(splited[2]));
            if (splited[0].charAt(0)=='-') transaction.setType("Paid");
            else transaction.setType("Received");
            list.add(transaction);
        }
        return list;
    }
}

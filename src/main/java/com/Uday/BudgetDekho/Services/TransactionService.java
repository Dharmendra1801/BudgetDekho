package com.Uday.BudgetDekho.Services;

import com.Uday.BudgetDekho.Model.Transaction;
import com.Uday.BudgetDekho.Repo.TimeDateRepo;
import com.Uday.BudgetDekho.Repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    ManiService maniService;

    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    AmountService amountService;

    @Autowired
    TimeDateService timeDateService;

    public List<Transaction> convertToObj(List<String> resultant) {
        List<Transaction> list = new ArrayList<>();
        for (String t: resultant) {
            String[] splitted = t.split("_");
            System.out.println(t);
            Transaction transaction = new Transaction();
            transaction.setAmount(Double.parseDouble(splitted[0]));
            transaction.setOtherPerson(splitted[1]);
            transaction.setDate(maniService.formatDate(splitted[2]));
            if (splitted[0].charAt(0)=='-') transaction.setType("Paid");
            else transaction.setType("Received");
            list.add(transaction);
        }
        return list;
    }

    public void save(List<String> resultant, Long time, String date) {
        List<Transaction> list = convertToObj(resultant);
        amountService.updateAmount(list);
        transactionRepo.saveAll(list);
        timeDateService.saveDate(date,time);
    }

    public List<Transaction> getAll() {
        return transactionRepo.findAll();
    }

    public Transaction getTransaction(Long x) {
        return transactionRepo.findById(x).orElse(null);
    }

    public void deleteTransaction(String x) {
        Transaction transaction = getTransaction(Long.parseLong(x));
        amountService.reverseTransaction(transaction);
        transactionRepo.deleteById(Long.parseLong(x));
    }

    public void reverseTransactions(String x) {
        String[] ids = x.split(",");
        for (String id: ids) {
            deleteTransaction(id);
        }
    }
}

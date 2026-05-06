package com.Uday.BudgetDekho.Services;

import com.Uday.BudgetDekho.Model.Amount;
import com.Uday.BudgetDekho.Model.Transaction;
import com.Uday.BudgetDekho.Repo.AmountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmountService {

    @Autowired
    AmountRepo amountRepo;

    public void updateCurrentAmount(Double amt) {
        amountRepo.deleteById("current");
        Amount amount = new Amount();
        amount.setType("current");
        amount.setAmount(amt);
        amountRepo.save(amount);
    }

    public void updateAmount(List<Transaction> list) {
        Double current = getCurrentAmount();
        Double totalSpent = getTotalSpent();
        Double totalReceived = getTotalReceived();

        for (Transaction t: list) {
            current+=t.getAmount();
            if (t.getAmount()<0) {
                totalSpent-=t.getAmount();
            }
            else totalReceived+=t.getAmount();
        }
        save(current,"current");
        save(totalSpent,"spent");
        save(totalReceived,"received");
    }

    private void save(Double amt, String type) {
        Amount amount = new Amount();
        amount.setAmount(amt);
        amount.setType(type);
        amountRepo.deleteById(type);
        amountRepo.save(amount);
    }

    public Double getCurrentAmount() {
        Amount amount = amountRepo.findById("current").orElse(null);
        return amount==null? null: amount.getAmount();
    }

    public Double getTotalSpent() {
        Amount amount = amountRepo.findById("spent").orElse(null);
        return amount==null? 0: amount.getAmount();
    }

    public Double getTotalReceived() {
        Amount amount = amountRepo.findById("received").orElse(null);
        return amount==null? 0: amount.getAmount();
    }

    public void reverseTransaction(Transaction transaction) {
        Double amt = getCurrentAmount();
        Double revAmt = transaction.getAmount();
        amt -= revAmt;
        save(amt,"current");
        if (revAmt<0) {
            Double amtSpent = getTotalSpent();
            amtSpent += revAmt;
            save(amtSpent,"spent");
        }
        else {
            Double amtRec = getTotalSpent();
            amtRec -= revAmt;
            save(amtRec,"received");
        }
    }
}

package com.Uday.BudgetDekho.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TotalBalance {
    @Id
    private Double totalBalance;
}

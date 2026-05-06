package com.Uday.BudgetDekho.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Amount {
    @Id
    private String type;
    private Double amount;
}

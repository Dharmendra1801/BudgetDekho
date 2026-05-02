package com.Uday.BudgetDekho.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Budget {
    @Id
    private String name;
    private Double ratio;
    private Double minimum;
    private Double current;
}

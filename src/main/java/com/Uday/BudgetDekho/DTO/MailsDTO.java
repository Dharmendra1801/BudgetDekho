package com.Uday.BudgetDekho.DTO;

import lombok.Data;

import java.util.List;

@Data
public class MailsDTO {
    private List<String> spentMails;
    private List<String> earnedMails;
    private List<Long> spentMailsTime;
    private List<Long> earnedMailsTime;
}

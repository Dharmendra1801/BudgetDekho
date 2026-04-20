package com.Uday.BudgetDekho.Controllers;

import com.Uday.BudgetDekho.Services.GmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gmail")
public class GmailController {

    private final GmailService gmailService;

    public GmailController(GmailService gmailService) {
        this.gmailService = gmailService;
    }

    @GetMapping("/read")
    public String readEmails() throws Exception {
        gmailService.readEmails();
        return "Emails fetched successfully";
    }
}

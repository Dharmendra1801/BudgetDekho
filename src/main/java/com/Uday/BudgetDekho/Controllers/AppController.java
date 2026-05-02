package com.Uday.BudgetDekho.Controllers;

import com.Uday.BudgetDekho.Config.GmailConfig;
import com.Uday.BudgetDekho.Services.GmailService;
import com.google.api.client.auth.oauth2.Credential;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    GmailService gmailService;

    @GetMapping("/")
    public void start(HttpServletResponse response) throws Exception {
        if (!gmailService.checkConn()) {
            response.sendRedirect("/auth/google");
            return;
        }
        response.sendRedirect("/api/budget/");
    }
}

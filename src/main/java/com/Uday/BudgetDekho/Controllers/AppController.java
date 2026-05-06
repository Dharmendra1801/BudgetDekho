package com.Uday.BudgetDekho.Controllers;

import com.Uday.BudgetDekho.Config.GmailConfig;
import com.Uday.BudgetDekho.Services.BudgetService;
import com.Uday.BudgetDekho.Services.GmailService;
import com.google.api.client.auth.oauth2.Credential;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    GmailService gmailService;

    @Autowired
    BudgetService budgetService;

    @GetMapping("/")
    public void start(HttpServletResponse response) throws Exception {
        if (!gmailService.checkConn()) {
            response.sendRedirect("/auth/google");
            return;
        }
        response.sendRedirect("/api/budget/");
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> isFirstTime() {
        if (budgetService.isFirstTime()) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }
}

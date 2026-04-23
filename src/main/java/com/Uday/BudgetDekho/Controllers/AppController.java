package com.Uday.BudgetDekho.Controllers;

import com.Uday.BudgetDekho.Config.GmailConfig;
import com.google.api.client.auth.oauth2.Credential;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppController {

    @GetMapping("/")
    public void start(HttpServletResponse response) throws Exception {
        Credential credential = GmailConfig.getStoredCredential();
        if (credential==null) {
            response.sendRedirect("/auth/google");
            return;
        }
        response.sendRedirect("/api/budget/refresh");
    }
}

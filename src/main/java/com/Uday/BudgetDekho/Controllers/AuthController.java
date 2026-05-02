package com.Uday.BudgetDekho.Controllers;

import com.Uday.BudgetDekho.Config.GmailConfig;
import com.google.api.client.googleapis.auth.oauth2.*;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @GetMapping("/auth/google")
    public void login(HttpServletResponse response) throws Exception {

        GoogleAuthorizationCodeFlow flow = GmailConfig.getFlow();

        String url = flow.newAuthorizationUrl()
                .setRedirectUri("http://localhost:8080/oauth/callback")
                .build();

        response.sendRedirect(url);
    }

    @GetMapping("/oauth/callback")
    public void callback(@RequestParam String code, HttpServletResponse response) throws Exception {

        GoogleAuthorizationCodeFlow flow = GmailConfig.getFlow();

        GoogleTokenResponse tokenResponse = flow.newTokenRequest(code)
                .setRedirectUri("http://localhost:8080/oauth/callback")
                .execute();

        flow.createAndStoreCredential(tokenResponse, "user");

        response.sendRedirect("/api/budget/");
    }
}

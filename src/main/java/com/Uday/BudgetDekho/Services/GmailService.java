package com.Uday.BudgetDekho.Services;

import com.Uday.BudgetDekho.Config.GmailConfig;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GmailService {

    public void readEmails() throws Exception {

        var httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        Gmail service = new Gmail.Builder(
                httpTransport,
                GsonFactory.getDefaultInstance(),
                GmailConfig.getCredentials()
        ).setApplicationName("gmail-reader").build();

        ListMessagesResponse response = service.users().messages()
                .list("me")
                .setQ("subject:❗ You have done a UPI txn. Check details! after:2026/04/18")
                .setMaxResults(20L)
                .execute();

        List<Message> messages = response.getMessages();

        if (messages != null) {
            for (Message msg : messages) {
                Message fullMsg = service.users().messages()
                        .get("me", msg.getId())
                        .execute();

                System.out.println(fullMsg.getSnippet());
            }
        }
    }
}
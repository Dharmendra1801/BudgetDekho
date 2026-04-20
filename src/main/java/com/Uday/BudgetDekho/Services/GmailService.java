package com.Uday.BudgetDekho.Services;

import com.Uday.BudgetDekho.Config.GmailConfig;
import com.Uday.BudgetDekho.Model.TimeDate;
import com.Uday.BudgetDekho.Repo.TimeDateRepo;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GmailService {

    @Autowired
    TimeDateRepo timeDateRepo;

    public void readEmails() throws Exception {

        var httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        Gmail service = new Gmail.Builder(
                httpTransport,
                GsonFactory.getDefaultInstance(),
                GmailConfig.getCredentials()
        ).setApplicationName("gmail-reader").build();

        ListMessagesResponse response = service.users().messages()
                .list("me")
                .setQ("subject:❗ You have done a UPI txn. Check details! after:2026/04/20")
                .setMaxResults(20L)
                .execute();

        List<Message> messages = response.getMessages();

        if (messages != null) {
            for (Message msg : messages) {
                Message fullMsg = service.users().messages()
                        .get("me", msg.getId())
                        .execute();

                System.out.println(fullMsg.getSnippet());
                TimeDate timeDate = new TimeDate();
                timeDate.setDate("20 April");
                timeDate.setTime(fullMsg.getSnippet());
                timeDateRepo.save(timeDate);
            }
        }
    }

    public List<TimeDate> printEmail() {
        System.out.println("___________________________________________________");
        return timeDateRepo.findAll();
    }
}
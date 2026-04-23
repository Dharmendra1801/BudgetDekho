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

import java.util.ArrayList;
import java.util.List;

@Service
public class GmailService {

    @Autowired
    TimeDateRepo timeDateRepo;

    public List<String> getSpentMails(String date) throws Exception {

        var httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        List<String> messages = new ArrayList<>();

        Gmail service = new Gmail.Builder(
                httpTransport,
                GsonFactory.getDefaultInstance(),
                GmailConfig.getStoredCredential()
        ).setApplicationName("gmail-reader").build();

        ListMessagesResponse response = service.users().messages()
                .list("me")
                .setQ("subject:❗ You have done a UPI txn. Check details! after:"+date)
                .setMaxResults(20L)
                .execute();

        List<Message> messagesId = response.getMessages();

        if (messagesId != null) {
            for (Message msgId : messagesId) {
                Message fullMsg = service.users().messages()
                        .get("me", msgId.getId())
                        .execute();

                messages.add(fullMsg.getSnippet());
            }
        }

        return messages;
    }

    public List<String> getEarnedMails(String date) throws Exception {

        var httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        List<String> messages = new ArrayList<>();

        Gmail service = new Gmail.Builder(
                httpTransport,
                GsonFactory.getDefaultInstance(),
                GmailConfig.getStoredCredential()
        ).setApplicationName("gmail-reader").build();

        ListMessagesResponse response = service.users().messages()
                .list("me")
                .setQ("subject:View: Account update for your HDFC Bank A/c after:"+date)
                .setMaxResults(20L)
                .execute();

        List<Message> messagesId = response.getMessages();

        if (messagesId != null) {
            for (Message msgId : messagesId) {
                Message fullMsg = service.users().messages()
                        .get("me", msgId.getId())
                        .execute();

                messages.add(fullMsg.getSnippet());
            }
        }

        return messages;
    }

}
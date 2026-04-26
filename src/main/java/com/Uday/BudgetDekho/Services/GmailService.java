package com.Uday.BudgetDekho.Services;

import com.Uday.BudgetDekho.Config.GmailConfig;
import com.Uday.BudgetDekho.DTO.MailsDTO;
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
    TimeDateService timeDateService;

    public MailsDTO getMails(String date) throws Exception {

        var httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        var credential = GmailConfig.getStoredCredential();

        if (credential.getExpiresInSeconds() == null || credential.getExpiresInSeconds() <= 60) {
            credential.refreshToken();
        }

        Gmail service = new Gmail.Builder(
                httpTransport,
                GsonFactory.getDefaultInstance(),
                credential
        ).setApplicationName("gmail-reader").build();

        ListMessagesResponse responseSpent = service.users().messages()
                .list("me")
                .setQ("subject:❗ You have done a UPI txn. Check details! after:"+date)
                .setMaxResults(20L)
                .execute();


        ListMessagesResponse responseEarned = service.users().messages()
                .list("me")
                .setQ("subject:View: Account update for your HDFC Bank A/c after:"+date)
                .setMaxResults(20L)
                .execute();

        timeDateService.saveDate();

        List<Message> spentMessagesId = responseSpent.getMessages();
        List<Message> earnedMessagesId = responseEarned.getMessages();

        List<String> spentMessages = new ArrayList<>();
        List<String> earnedMessages = new ArrayList<>();

        List<Long> spentMessagesTime = new ArrayList<>();
        List<Long> earnedMessagesTime = new ArrayList<>();

        if (spentMessagesId != null) {
            for (Message msgId : spentMessagesId) {
                Message fullMsg = service.users().messages()
                        .get("me", msgId.getId())
                        .execute();

                spentMessages.add(fullMsg.getSnippet());
                spentMessagesTime.add(fullMsg.getInternalDate());
            }
        }

        if (earnedMessagesId != null) {
            for (Message msgId : earnedMessagesId) {
                Message fullMsg = service.users().messages()
                        .get("me", msgId.getId())
                        .execute();

                earnedMessages.add(fullMsg.getSnippet());
                earnedMessagesTime.add(fullMsg.getInternalDate());
            }
        }

        return createObj(spentMessages,earnedMessages,spentMessagesTime,earnedMessagesTime);
    }

    private MailsDTO createObj(List<String> spentMessages, List<String> earnedMessages, List<Long> spentMessagesTime, List<Long> earnedMessagesTime) {
        MailsDTO mailsDTO = new MailsDTO();
        mailsDTO.setEarnedMails(earnedMessages);
        mailsDTO.setSpentMails(spentMessages);
        mailsDTO.setSpentMailsTime(spentMessagesTime);
        mailsDTO.setEarnedMailsTime(earnedMessagesTime);
        return mailsDTO;
    }

}
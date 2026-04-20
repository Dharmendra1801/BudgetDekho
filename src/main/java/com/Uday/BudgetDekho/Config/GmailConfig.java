package com.Uday.BudgetDekho.Config;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.InputStreamReader;
import java.util.Collections;

public class GmailConfig {

    private static final String APPLICATION_NAME = "gmail-reader";
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    public static Credential getCredentials() throws Exception {

        var httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JSON_FACTORY,
                new InputStreamReader(
                        GmailConfig.class.getResourceAsStream("/credentials.json")
                )
        );

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport,
                JSON_FACTORY,
                clientSecrets,
                Collections.singleton("https://www.googleapis.com/auth/gmail.readonly")
        )
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();

        return new AuthorizationCodeInstalledApp(
                flow,
                new LocalServerReceiver.Builder()
                        .setPort(8888)
                        .build()
        ).authorize("user");
    }
}
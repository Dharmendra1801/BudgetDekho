package com.Uday.BudgetDekho.Config;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Objects;

public class GmailConfig {

    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    public static GoogleAuthorizationCodeFlow getFlow() throws Exception {

        var httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JSON_FACTORY,
                new InputStreamReader(
                        Objects.requireNonNull(GmailConfig.class.getResourceAsStream("/credentials.json"))
                )
        );

        return new GoogleAuthorizationCodeFlow.Builder(
                httpTransport,
                JSON_FACTORY,
                clientSecrets,
                Collections.singleton("https://www.googleapis.com/auth/gmail.readonly")
        )
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
    }

    public static Credential getStoredCredential() throws Exception {
        return getFlow().loadCredential("user");
    }
}
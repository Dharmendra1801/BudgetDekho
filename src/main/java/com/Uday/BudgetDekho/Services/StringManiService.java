package com.Uday.BudgetDekho.Services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StringManiService {
    public List<String> getDataFromMails(List<String> spent, List<String> earned) {
        List<String> result = new ArrayList<>();
        getDataFromSpentMails(spent,result);
        getDataFromEarnedMails(earned,result);
        return result;
    }

    private void getDataFromSpentMails(List<String> spent, List<String> result) {
        final String preAmount = "Dear Customer, Rs.";
        final String postAmount = "has been debited from account 4296 to VPA ";
        final String on = "on ";
        for (String message: spent) {
            StringBuilder sb = new StringBuilder();
            int x = 0;
            for (int i=0; i<3; i++) {
                switch (i) {
                    case 0:
                        StringBuilder temp0 = new StringBuilder("-");
                        for (x+=preAmount.length(); x<message.length(); x++) {
                            char c = message.charAt(x);
                            if (c==' ') break;
                            temp0.append(c);
                        }
                        sb.append(temp0).append('_');
                        break;
                    case 1:
                        StringBuilder temp1 = new StringBuilder();
                        x+=postAmount.length()+1;
                        while (x<message.length() && message.charAt(x)!=' ') x++;
                        for (x+=1; x<message.length(); x++) {
                            char c = message.charAt(x);
                            if (c==' ' && message.charAt(x+6)=='-') break;
                            temp1.append(c);
                        }
                        sb.append(temp1).append('_');
                        break;
                    case 2:
                        StringBuilder temp2 = new StringBuilder();
                        for (x+=on.length()+1; x<message.length(); x++) {
                            char c = message.charAt(x);
                            if (c=='.') break;
                            temp2.append(c);
                        }
                        sb.append(temp2);
                        break;
                }
            }
            result.add(sb.toString());
        }
    }

    private void getDataFromEarnedMails(List<String> spent, List<String> result) {
        final String preAmount = "Dear Customer, Rs. ";
        final String postAmount = "is successfully credited to your account **4296 by VPA ";
        final String on = "on ";
        for (String message: spent) {
            StringBuilder sb = new StringBuilder();
            int x = 0;
            for (int i=0; i<3; i++) {
                switch (i) {
                    case 0:
                        StringBuilder temp0 = new StringBuilder();
                        for (x+=preAmount.length(); x<message.length(); x++) {
                            char c = message.charAt(x);
                            if (c==' ') break;
                            temp0.append(c);
                        }
                        sb.append(temp0).append('_');
                        break;
                    case 1:
                        StringBuilder temp1 = new StringBuilder();
                        x+=postAmount.length()+1;
                        while (x<message.length() && message.charAt(x)!=' ') x++;
                        for (x+=1; x<message.length(); x++) {
                            char c = message.charAt(x);
                            if (c==' ' && message.charAt(x+6)=='-') break;
                            temp1.append(c);
                        }
                        sb.append(temp1).append('_');
                        break;
                    case 2:
                        StringBuilder temp2 = new StringBuilder();
                        for (x+=on.length()+1; x<message.length(); x++) {
                            char c = message.charAt(x);
                            if (c=='.') break;
                            temp2.append(c);
                        }
                        sb.append(temp2);
                        break;
                }
            }
            result.add(sb.toString());
        }
    }

    public String formatDate(String s) {
        StringBuilder date = new StringBuilder();
        String[] split = s.split("-");
        date.append("20").append(split[2]);
        date.append("/").append(split[1]);
        date.append("/").append(split[0]);
        return date.toString();
    }
}

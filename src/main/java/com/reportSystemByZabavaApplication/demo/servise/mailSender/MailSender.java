package com.reportSystemByZabavaApplication.demo.servise.mailSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailSender {
    private static final String mailAddressRegEx = "\\b[A-Za-z0-9._%+-]+@[a-zA-Z]+\\.[a-zA-Z]{2,4}\\b";

    public static boolean checkMailCorrect(String mail) {
        Matcher matcher = Pattern.compile(mailAddressRegEx).matcher(mail);
        matcher.find();
        return matcher.group().equals(mail);
    }


}

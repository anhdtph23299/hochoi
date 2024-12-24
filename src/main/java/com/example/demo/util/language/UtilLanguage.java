package com.example.demo.util.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UtilLanguage {

    private static MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        UtilLanguage.messageSource = messageSource;
    }

    public static String getLanguage(String message) {
        try {
            return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return message;
        }
    }

    public static String getLanguageParameter(String message, String... parameter) {
        try {
            return messageSource.getMessage(message, parameter, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return message;
        }
    }

}

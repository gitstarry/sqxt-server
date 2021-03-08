package com.cqut.why.authoringsystem.authoringsystem.config.util.message;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageUtil {
    private static MessageSource messageSource;

    static{
        messageSource = ApplicationContextUtil.getApplicationContext().getBean(MessageSource.class);
    }

    public static String getMessage(String key, Object... args){
        return messageSource.getMessage(key,args, LocaleContextHolder.getLocale());
    }
}

package br.com.project.msvotacao.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageSourceUtil {

    private final MessageSource messageSource;
    private static final Locale BR = new Locale("pt", "BR");

    public String message(String messageCode) {
        return messageSource.getMessage(messageCode, null, BR);
    }

}

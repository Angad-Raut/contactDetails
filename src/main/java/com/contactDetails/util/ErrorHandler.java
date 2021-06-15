package com.contactDetails.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * @author angad
 * Date : 14 June,2021
 * This error handler class.
 */
@Component
public class ErrorHandler {

    /**
     * This is used for to convert into local message.
     */
    @Autowired
    private MessageSource messageSource;
    
    public String getMessageLocal(String message) {
        if (Objects.isNull(message)) {
            return "An unexpected error has occurred("+message+")";
        }
        Locale locale = LocaleContextHolder.getLocale();
        String result = messageSource.getMessage(message, null, locale);
        return result;
    }
    
    /**
     * This is used for to convert into error message.
     * @param result
     * @return
     */
    public Map<String, String> getErrorMessageMap(BindingResult result) {
        Map<String, String> errorMap = new HashMap<String, String>();

        Set<String> errorMsgList = new HashSet<String>();

        List<FieldError> list1 = result.getFieldErrors();

        List<ObjectError> objectErrorList = result.getAllErrors();

        for (FieldError fieldError : list1) {
            errorMap.put(fieldError.getField(), getMessageLocal(fieldError.getDefaultMessage()));
            errorMsgList.add(fieldError.getDefaultMessage());
        }

        for (ObjectError objectError : objectErrorList) {
            if (!errorMsgList.contains(objectError.getDefaultMessage()))
                errorMap.put("common", getMessageLocal(objectError.getDefaultMessage()));
        }

        return errorMap;

    }
}

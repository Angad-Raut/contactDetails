package com.contactDetails.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author angad
 * Date : 14 June 2021
 * This class used for to fetch custom error messages from properties file.
 */
@Component
@PropertySource(value={"classpath:/application.properties","classpath:/constants.properties"})
public class MessageHandler {

          @Value("${CONTACT_DETAILS_NOT_FOUND_BY_ID}")
           public String CONTACT_DETAILS_NOT_FOUND_BY_ID;
          @Value("${MOBILE_NUMBER_ALREADY_PRESENT}")
           public String MOBILE_NUMBER_ALREADY_PRESENT;
          @Value("${EMAIL_ID_ALREADY_PRESENT}")
           public String  EMAIL_ID_ALREADY_PRESENT;
}

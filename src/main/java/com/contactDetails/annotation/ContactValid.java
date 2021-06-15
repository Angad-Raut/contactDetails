package com.contactDetails.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.contactDetails.validator.ContactDetailsValidator;

/**
 * @author angad
 * Date : 14 June 2021
 * This is contact details request custom annotation.
 */
@Documented
@Constraint(validatedBy=ContactDetailsValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ContactValid {
 
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

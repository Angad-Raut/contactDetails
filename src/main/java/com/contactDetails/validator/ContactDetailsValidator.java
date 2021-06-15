package com.contactDetails.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.contactDetails.annotation.ContactValid;
import com.contactDetails.dto.ContactDto;
import com.contactDetails.util.BasicConversion;

/**
 * @author angad
 * Date : 14 June 2021
 * This class used for to check request validations.
 */
public class ContactDetailsValidator  implements ConstraintValidator<ContactValid, ContactDto> {

    /**
     * This method used for to validate the request parameters validation.
     */
    @Override
    public boolean isValid(ContactDto value, ConstraintValidatorContext context) {
        Boolean result=true;
        if(value.getFirstName()==null){
            context.buildConstraintViolationWithTemplate("firstname.not.null").addPropertyNode("firstName")
            .addConstraintViolation();
            result=false;
        }else if(value.getFirstName().length()>50){
            context.buildConstraintViolationWithTemplate("firstname.exceeds.max.size").addPropertyNode("firstName")
            .addConstraintViolation();
            result=false;
        }
        if(value.getLastName()==null){
            context.buildConstraintViolationWithTemplate("lastname.not.null").addPropertyNode("lastName")
            .addConstraintViolation();
            result=false;
        }else if(value.getLastName().length()>50){
            context.buildConstraintViolationWithTemplate("lastname.exceeds.max.size").addPropertyNode("lastName")
            .addConstraintViolation();
            result=false;
        }
        if(value.getEmail()==null){
            context.buildConstraintViolationWithTemplate("email.not.null").addPropertyNode("email")
            .addConstraintViolation();
            result=false;
        }else if(!BasicConversion.isValidEmail(value.getEmail())){
            context.buildConstraintViolationWithTemplate("email.not.valid").addPropertyNode("email")
            .addConstraintViolation();
            result=false;
        }
        if(value.getMobile()==null){
            context.buildConstraintViolationWithTemplate("mobile.not.null").addPropertyNode("mobile")
            .addConstraintViolation();
            result=false;
        }else if(!BasicConversion.isValidMobile(value.getMobile())){
            context.buildConstraintViolationWithTemplate("mobile.not.valid").addPropertyNode("mobile")
            .addConstraintViolation();
            result=false;
        }
        return result;
    }

}

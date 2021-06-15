package com.contactDetails.util;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

/**
 * @author angad
 * Date : 14 June,2021
 * This class used for some conversion.
 */
@Component
public class BasicConversion {
	public static final BigInteger EXISTS = BigInteger.valueOf(1l);
	public static final BigInteger NOT_EXISTS = BigInteger.valueOf(0l);
	public static final String DASH="-";

	/**
	 * This method used for to check record is exist.
	 * @param value
	 * @return
	 */
	public static Boolean isRecordExist(BigInteger value) {
        if(value.equals(EXISTS))
               return true;
        else 
               return false;
    }
	
	/**
	 * This method used for to check record is not exist.
	 * @param value
	 * @return
	 */
	public static Boolean isRecordNotExist(BigInteger value) {
        if(value.equals(NOT_EXISTS))
               return true;
        else 
               return false;
    }
	
	/**
	 * This method used for to check is email valid or not.
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email){
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(matcher.matches()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * This method used for to check is mobile valid or not.
	 * @param mobile
	 * @return
	 */
	public static boolean isValidMobile(Long mobile){
		  String regex ="\\d{10}";
		  String mobileNo = String.valueOf(mobile);
		  Pattern pattern = Pattern.compile(regex);
	      Matcher matcher = pattern.matcher(mobileNo);
	      if (matcher.matches()) {
	    	  return true;
	      } else {
	    	  return false;
	      }
	}

	/**
	 * This method used for to return the status.
	 * @param status
	 * @return
	 */
	public static String getStatus(Boolean status){
		  if(status){
			  return "Active";
		  }else{
			  return "Inactive"; 
		  }
	}
}

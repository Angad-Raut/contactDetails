package com.contactDetails.exception;

/**
 * @author angad
 * Date : 14 June 2021
 * This is custom  InvalidDataException class.
 */
public class InvalidDataException extends RuntimeException{
	private static final long serialVersionUID = -8611171344913703153L;
	public InvalidDataException(String message){
    	 super(message);
     }
}

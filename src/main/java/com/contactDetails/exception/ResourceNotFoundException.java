package com.contactDetails.exception;

/**
 * @author angad
 * Date : 14 June 2021
 * This is custom  ResourceNotFoundException class.
 */
public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -3204056675877390170L;
	public ResourceNotFoundException(String message){
		     super(message);
	  }
}

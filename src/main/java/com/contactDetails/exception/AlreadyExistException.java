package com.contactDetails.exception;

/**
 * @author angad
 * Date : 14 June 2021
 * This is custom  AlreadyExistException class.
 */
public class AlreadyExistException extends RuntimeException{
	private static final long serialVersionUID = 4447719557743312849L;
	public AlreadyExistException(String message){
    	    super(message);
      }
}

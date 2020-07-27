package com.jp.genesis.exception;

/*
 * This is service level exception class, extends Exception class. 
 *   
 */

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * Class constructor with message.
	 * 
	 * @param message exception message.
	 */
	public ServiceException(String message){
		super(message);
	}
	/**
	 * Class constructor with message and instance of Throwable
	 * 
	 * @param message exception message.
	 * @param cause instance of Throwable
	 */
	public ServiceException(String message, Throwable cause){
		super(message, cause);
	}
}

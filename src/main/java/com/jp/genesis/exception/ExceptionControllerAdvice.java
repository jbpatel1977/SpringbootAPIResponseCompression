package com.jp.genesis.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jp.genesis.bean.ErrorResponse;


/*
 *   ExceptionControllerAdvice class to global code to handle different type of exceptions. It apply globally to all controllers.
 *   
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
	
	private static final long serialVersionUID = 1L;
	public static final Logger LOG = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	/**
	 * Method handle Exception.class thrown from any controllers. 
	 * 
	 * @param ex Instance of Exception class.
	 * @return ResponseEntity<ErrorResponse> filled with error code, status and other details in ErrorResponse object.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		LOG.error("inside exceptionHandler @ControllerAdvice - Exception.class");
		LOG.error(ex.getMessage());
		ErrorResponse errorRes = new ErrorResponse();
		errorRes.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		errorRes.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorRes.setMessage(ex.getMessage());;
	
		return new ResponseEntity<ErrorResponse>(errorRes,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(ServiceException ex) {
		LOG.error("inside exceptionHandler @ControllerAdvice - ServiceException.class");
		LOG.error(ex.getMessage());
		ErrorResponse errorRes = new ErrorResponse();
		errorRes.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		errorRes.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorRes.setMessage(ex.getMessage());;
	
		return new ResponseEntity<ErrorResponse>(errorRes,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(ResourceNotFound ex) {
		LOG.error("inside exceptionHandler @ControllerAdvice - ResourceNotFound.class");
		LOG.error(ex.getMessage());
		ErrorResponse errorRes = new ErrorResponse();
		errorRes.setHttpStatus(HttpStatus.NOT_FOUND.toString());
		errorRes.setHttpCode(HttpStatus.NOT_FOUND.value());
		errorRes.setMessage(ex.getMessage());;
	
		return new ResponseEntity<ErrorResponse>(errorRes,HttpStatus.NOT_FOUND);
	}
}

package com.usecase.springbootcrudApp.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.usecase.springbootcrudApp.model.ExceptionResponse;
import com.usecase.springbootcrudApp.util.Constants;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler({NullPointerException.class,Exception.class})
	public final ResponseEntity<Object> handleGeneralException(Exception e){
		List<String> errors =  new ArrayList<>();
		errors.add(e.getLocalizedMessage());
		return new ResponseEntity<Object>(new ExceptionResponse(Constants.INTERNAL_ERROR,errors), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<Object> recordNotFoundException(RecordNotFoundException e){
		List<String> errors =  new ArrayList<>();
		errors.add(e.getLocalizedMessage());
		return new ResponseEntity<Object>(new ExceptionResponse(Constants.USER_NOT_FOUND,errors), HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request){
		List<String> errors =  new ArrayList<>();
		for(ObjectError er : e.getBindingResult().getAllErrors()){
			errors.add(er.getDefaultMessage());
		}
		return new ResponseEntity<Object>(new ExceptionResponse(Constants.VALIDATION_EX,errors), HttpStatus.BAD_REQUEST);	
	}

}

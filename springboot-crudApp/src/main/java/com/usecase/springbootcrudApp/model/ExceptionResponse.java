package com.usecase.springbootcrudApp.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {

	private String errorCode;
	private String errorMessage;
	private List<String> errorDetails;
	public ExceptionResponse(String errorMessage, List<String> errorDetails) {
		this.errorMessage = errorMessage;
		this.errorDetails = errorDetails;
	}


}

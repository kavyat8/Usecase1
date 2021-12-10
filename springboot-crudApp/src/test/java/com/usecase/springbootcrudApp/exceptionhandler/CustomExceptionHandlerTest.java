package com.usecase.springbootcrudApp.exceptionhandler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)
public class CustomExceptionHandlerTest {
	
	@InjectMocks
	CustomExceptionHandler handler;
	
	@Test
	public void testhandleGeneralException(){
		Assertions.assertNotNull(handler.handleGeneralException(new Exception()));
	}
	
	
	@Test
	public void testrecordNotFoundException(){
		Assertions.assertNotNull(handler.recordNotFoundException(new RecordNotFoundException("record not found")));
	}
	
}

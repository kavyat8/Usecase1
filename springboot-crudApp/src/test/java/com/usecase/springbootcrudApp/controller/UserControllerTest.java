package com.usecase.springbootcrudApp.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.usecase.springbootcrudApp.entity.UserEntity;
import com.usecase.springbootcrudApp.service.UserService;

@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	
	@InjectMocks
	UserController controller;
		
	@Mock
	UserService service;
	
	@Test
	public void testgetuser(){
		UserEntity user = new UserEntity(1234,"firstName","LastName","email");
		Mockito.when(service.getUser(Mockito.anyLong())).thenReturn(user);
		Assertions.assertNotNull(controller.getUser((long) 1234));
	}
	
	@Test
	public void testcreateUser(){
		UserEntity user = new UserEntity(1234,"firstName","LastName","email");
		Mockito.when(service.createorUpdateUser(Mockito.any())).thenReturn(user);
		Assertions.assertNotNull(controller.addUser(user));
	}
	
	@Test
	public void testUpdateUser(){
		UserEntity user = new UserEntity(1234,"firstName","LastName","email");
		Mockito.when(service.createorUpdateUser(Mockito.any())).thenReturn(user);
		Assertions.assertNotNull(controller.updateUserDetails(user));
	}
	
	@Test
	public void testDeleteUser(){
		Mockito.doNothing().when(service).deleteUserById(Mockito.any());
		controller.deleteUser((long) 1234);
		Mockito.verify(service, Mockito.times(1)).deleteUserById((long) 1234);
	}

}

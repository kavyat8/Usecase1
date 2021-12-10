package com.usecase.springbootcrudApp.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Optionals;

import com.usecase.springbootcrudApp.entity.UserEntity;
import com.usecase.springbootcrudApp.exceptionhandler.RecordNotFoundException;
import com.usecase.springbootcrudApp.repository.UserRepository;

@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@InjectMocks
	UserService service;
	
	@Mock
	UserRepository repo;
	
	@Test
	public void testGetUser(){
		UserEntity user = new UserEntity(1234,"firstName","LastName","email");
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
		Assertions.assertNotNull(service.getUser((long) 1234));
	}
	
	@Test
	public void testGetUserException(){
		
		Assertions.assertThrows(RecordNotFoundException.class,()-> service.getUser((long) 1235));
	}
	
	@Test
	public void testCreate(){
		UserEntity user = new UserEntity(1234,"firstName","LastName","email");
		Mockito.when(repo.save(user)).thenReturn(user);
		Assertions.assertNotNull(service.createorUpdateUser(user));
		
	}
	
	@Test
	public void testUpdate(){
		UserEntity user = new UserEntity(1234,"firstName","LastName","email");
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
		UserEntity newuser = new UserEntity(1234,"firstName1","LastName1","email");
		Mockito.when(repo.save(newuser)).thenReturn(newuser);
		Assertions.assertNotNull(service.createorUpdateUser(newuser));
		
	}
	
	@Test
	public void testdeleteUser(){
		UserEntity user = new UserEntity(1234,"firstName","LastName","email");
		Mockito.when(repo.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
		Mockito.doNothing().when(repo).deleteById(Mockito.anyLong());
		service.deleteUserById((long) 1234);
		Mockito.verify(repo, Mockito.timeout(1)).deleteById((long) 1234);
	}
	
	@Test
	public void testdeleteuserException(){
		//Mockito.doNothing().when(repo).deleteById(Mockito.anyLong());
		//service.deleteUserById((long) 1234);
		Assertions.assertThrows(RecordNotFoundException.class,()-> service.deleteUserById((long) 1234));
		//Mockito.verify(repo, Mockito.timeout(1)).deleteById((long) 1234);
	}

}

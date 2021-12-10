package com.usecase.springbootcrudApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.usecase.springbootcrudApp.entity.UserEntity;
import com.usecase.springbootcrudApp.logging.ApplicationLogger;
import com.usecase.springbootcrudApp.service.UserService;
import com.usecase.springbootcrudApp.util.Constants;


@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	@ApplicationLogger
	@GetMapping(value="/getuser/{id}")
	public ResponseEntity<UserEntity> getUser(@PathVariable("id") Long id){
		UserEntity user = service.getUser(id);
		return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
	}
	
	@ApplicationLogger
	@PostMapping(value="/adduser")
	public ResponseEntity<UserEntity> addUser(@Valid @RequestBody UserEntity user){
		UserEntity updatedUser = service.createorUpdateUser(user);
		return new ResponseEntity<UserEntity>(updatedUser, HttpStatus.OK);
		
	}
	
	@ApplicationLogger
	@PutMapping(value="/updateUser")
	public ResponseEntity<UserEntity> updateUserDetails(@Valid @RequestBody UserEntity user){
		UserEntity updatedUser = service.createorUpdateUser(user);
		return new ResponseEntity<UserEntity>(updatedUser, HttpStatus.OK);
		
	}
	
	@ApplicationLogger
	@DeleteMapping(value="/deleteUser/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id){
		service.deleteUserById(id);
		return new ResponseEntity<Object>(Constants.USER_DELETED, HttpStatus.OK);
		
	}

}

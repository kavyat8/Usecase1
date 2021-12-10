package com.usecase.springbootcrudApp.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usecase.springbootcrudApp.entity.UserEntity;
import com.usecase.springbootcrudApp.exceptionhandler.RecordNotFoundException;
import com.usecase.springbootcrudApp.logging.ApplicationLogger;
import com.usecase.springbootcrudApp.repository.UserRepository;
import com.usecase.springbootcrudApp.util.Constants;

@Service
public class UserService {
	
	private static final Logger LOG = LogManager.getLogger();
	
	@Autowired
	UserRepository repository;
	
	@ApplicationLogger
	public UserEntity getUser(Long id){
		Optional<UserEntity> user = repository.findById(id);
		if(user.isPresent()){
			LOG.info(Constants.USER_FOUND+id);
			return user.get();
		} else{
			throw new RecordNotFoundException(Constants.USER_NOT_FOUND+id);
		}
		
	}
	
	@ApplicationLogger
	public UserEntity createorUpdateUser(UserEntity user){
		Optional<UserEntity> userEntity = repository.findById(user.getId());
		if(userEntity.isPresent()){
			LOG.info(Constants.USER_FOUND+user.getId()+" updating user details");
			UserEntity newUser = userEntity.get();
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			
			newUser = repository.save(newUser);
			return newUser;
		}else{
			LOG.info(Constants.USER_NOT_FOUND+user.getId()+" inserting new user");
			user = repository.save(user);
			return user;
		}
		
		
	}
	
	@ApplicationLogger
	public void deleteUserById(Long id) {
		Optional<UserEntity> userEntity = repository.findById(id);
		if(userEntity.isPresent()){
			repository.deleteById(id);
			LOG.info(Constants.USER_DELETED+id);
		} else{
			throw new RecordNotFoundException(Constants.USER_NOT_FOUND);
		}
	}
	
	
	
	

}

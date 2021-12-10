package com.usecase.springbootcrudApp.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="User")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="firstName")
	@NotEmpty(message="first name must not be empty")
	private String firstName;
	
	@NotEmpty(message="last name must not be empty")
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="email")	
	@Email(message="Email should be a valid one")
	private String email;
	
	
}

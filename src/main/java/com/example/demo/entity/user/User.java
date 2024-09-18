package com.example.demo.entity.user;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class User {
	
	String userId;
	
	@NotBlank
	@Length(min=4,max=100)
	String userName;
	
	@NotNull
	@Length(min=4,max=100)
	@Pattern(regexp="^[a-zA-Z0-9]+$")
	String password;
	
	@Min(value=0)
	@NotNull
	int balance;
}
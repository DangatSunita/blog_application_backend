package com.lti.blog.paylods;


import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lti.blog.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

	private int id;
	
	@NotEmpty(message = "Name field should not be empty")
	@Size(min= 4, message= "Username must be min of 4 characters")
	private String name;
	
	@NotEmpty(message = "Email field should not be empty")
	@Email(message = "Your Email is not valid")
	private String email;

	@Size(min= 4, max=10, message="Password must be min of 4 characters and max 10 characters")
	@NotEmpty(message = "Password field should not be empty")
	private String password;

	@NotEmpty(message = "About field should not be empty")
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
	
	@JsonIgnore
	public String getPassword() {
		return this.password;
		
	}
	
	@JsonProperty
	public String setPassword(String password) {
		return this.password= password;
		
	}
}

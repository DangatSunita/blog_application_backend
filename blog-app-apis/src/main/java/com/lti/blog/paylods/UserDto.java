package com.lti.blog.paylods;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min= 4, message="Username must min of 4 characters")
	private String name;
	
	@Email(message = "Your Email is not valid")
	private String email;

	@Size(min= 4, max=10, message="Password must be min of 4 characters and max 10 characters")
	@NotEmpty
	private String password;

	@NotEmpty
	private String about;
}

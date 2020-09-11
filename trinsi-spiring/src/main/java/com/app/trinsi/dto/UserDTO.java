package com.app.trinsi.dto;

import com.app.trinsi.model.UserPlanner;
import com.app.trinsi.model.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Long id;

	@Email(message="Email should be valid")
	private String email;

	@NotBlank(message = "Name can not be empty string")
	private String name;

	@NotBlank(message = "Surname can not be empty string")
	private String surname;

	@NotBlank(message = "Username can not be empty string")
	private String username;

	@Size(min = 8, message = "Password must be at least {value}")
	private String password;

	private UserRole userRole;
	
}

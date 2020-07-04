package com.app.trinsi.dto;

import com.app.trinsi.model.UserPlanner;
import com.app.trinsi.model.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Long id;
	private String email;
	private String name;
	private String surname;
	private String username;
	private String password;
	private UserRole userRole;
	
}

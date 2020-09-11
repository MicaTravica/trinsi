package com.app.trinsi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

	@NotNull(message = "Username can not be null")
	private String username;

	@NotNull(message = "Password can not be null")
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;
}

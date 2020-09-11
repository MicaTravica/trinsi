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
public class PasswordChangeDTO {

	@NotNull(message = "Old password can not be null")
	@Size(min = 8, message = "Old password must be at least 8 characters")
	private String oldPassword;

	@NotNull(message = "New password can not be null")
	@Size(min = 8, message = "New password must be at least 8 characters")
	private String newPassword;
	
}

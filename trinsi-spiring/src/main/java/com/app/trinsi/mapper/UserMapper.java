package com.app.trinsi.mapper;

import com.app.trinsi.dto.UserDTO;
import com.app.trinsi.model.User;

public class UserMapper {

	public static UserDTO toDTO(User user) {
		return new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getSurname(), user.getUsername(),
				null, user.getUserRole());
	}
	
	public static User toUser(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getSurname(), userDto.getEmail(),
				false, userDto.getUsername(), userDto.getPassword(), userDto.getUserRole(), null,
				null);
	}
}

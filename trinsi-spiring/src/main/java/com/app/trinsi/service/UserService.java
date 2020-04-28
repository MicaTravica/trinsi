package com.app.trinsi.service;

import com.app.trinsi.dto.response.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findById(Long id);
    UserDTO findByUsername(String username);
    List<UserDTO> findAll();
}

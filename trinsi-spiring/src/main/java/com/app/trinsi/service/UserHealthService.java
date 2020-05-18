package com.app.trinsi.service;

import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.UserHealth;

public interface UserHealthService {

    UserHealth findById(Long id);

    UserHealth addHealth(UserHealth health, String username) throws ResourceNotFoundException;

}

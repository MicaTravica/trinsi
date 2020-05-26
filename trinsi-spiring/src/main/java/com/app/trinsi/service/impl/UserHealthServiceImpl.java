package com.app.trinsi.service.impl;

import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.UserHealth;
import com.app.trinsi.repository.UserHealthRepository;
import com.app.trinsi.service.UserHealthService;
import com.app.trinsi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserHealthServiceImpl implements UserHealthService {

    private final UserHealthRepository userHealthRepository;
    private final UserService userService;

    @Autowired
    public UserHealthServiceImpl(UserHealthRepository userHealthRepository, UserService userService) {
        this.userHealthRepository = userHealthRepository;
        this.userService = userService;
    }

    @Override
    public UserHealth findById(Long id) {
        return userHealthRepository.findById(id).orElse(new UserHealth());
    }

    @Override
    public UserHealth addHealth(UserHealth health, String username) throws ResourceNotFoundException {
        health.setId(null);
        UserHealth userHealth = userHealthRepository.save(health);
        userService.updateUserHealth(userHealth, username);
        return userHealth;
    }

}

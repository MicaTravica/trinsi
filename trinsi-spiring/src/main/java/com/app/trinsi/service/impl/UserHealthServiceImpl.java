package com.app.trinsi.service.impl;

import com.app.trinsi.model.UserHealth;
import com.app.trinsi.repository.UserHealthRepository;
import com.app.trinsi.service.UserHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserHealthServiceImpl implements UserHealthService {

    @Autowired
    private UserHealthRepository userHealthRepository;

    @Override
    public UserHealth findByUsername(Long id) {
        return userHealthRepository.findByUsername(id).orElse(null);
    }

    @Override
    public UserHealth addHealth(Object toHealth) {
        return null;
    }
}

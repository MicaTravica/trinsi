package com.app.trinsi.service;

import com.app.trinsi.model.UserHealth;

public interface UserHealthService {

    UserHealth findByUsername(Long id);

    UserHealth addHealth(Object toHealth);
}

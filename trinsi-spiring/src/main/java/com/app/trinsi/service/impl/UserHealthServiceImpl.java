package com.app.trinsi.service.impl;

import com.app.trinsi.exceptions.ResourceCantUpdateException;
import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.UserHealth;
import com.app.trinsi.repository.UserHealthRepository;
import com.app.trinsi.service.UserHealthService;
import com.app.trinsi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        health.setLastChanged(new Date());
        health.setPlannerTaken(false);
        UserHealth userHealth = userHealthRepository.save(health);
        userService.updateUserHealth(userHealth, username);
        return userHealth;
    }

    @Override
    public UserHealth updateHealth(UserHealth updateHealth) throws ResourceNotFoundException {
        UserHealth health = userHealthRepository.findById(updateHealth.getId())
                .orElseThrow(() -> new  ResourceNotFoundException("Health"));
        health.update(updateHealth);
        return userHealthRepository.save(health);
    }

    @Override
    public void addTime(Long id, int minutes) throws ResourceNotFoundException, ResourceCantUpdateException {
        UserHealth userHealth = userHealthRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Health"));
        if (userHealth.isPlannerTaken()) {
            userHealth.setHoursOfExercise(userHealth.getHoursOfExercise() + minutes / 60.0);
            userHealth.setPlannerTaken(false);
            userHealthRepository.save(userHealth);
        } else {
            throw new ResourceCantUpdateException("Time");
        }
    }

    @Override
    public void setPlannerIsTaken(UserHealth userHealth) {
        userHealth.setPlannerTaken(true);
        userHealthRepository.save(userHealth);
    }

}

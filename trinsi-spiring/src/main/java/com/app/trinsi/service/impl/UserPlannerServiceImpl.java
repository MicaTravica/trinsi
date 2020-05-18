package com.app.trinsi.service.impl;

import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.EXERCISE_TYPE;
import com.app.trinsi.model.User;
import com.app.trinsi.model.UserHealth;
import com.app.trinsi.model.UserPlanner;
import com.app.trinsi.repository.UserPlannerRepository;
import com.app.trinsi.service.UserHealthService;
import com.app.trinsi.service.UserPlannerService;
import com.app.trinsi.service.UserService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPlannerServiceImpl implements UserPlannerService {

    private final UserPlannerRepository userPlannerRepository;
    private final UserHealthService userHealthService;
    private final UserService userService;
    private final KieContainer kieContainer;

    @Autowired
    public UserPlannerServiceImpl(UserPlannerRepository userPlannerRepository, UserHealthService userHealthService,
                                  UserService userService, KieContainer kieContainer) {
        this.userPlannerRepository = userPlannerRepository;
        this.userHealthService = userHealthService;
        this.userService = userService;
        this.kieContainer = kieContainer;
    }

    @Override
    public UserPlanner getUserPlanner(UserPlanner userPlanner, UserHealth userHealth) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(userPlanner);
        kieSession.insert(userHealth);
        kieSession.fireAllRules();
        kieSession.dispose();
        return userPlanner;
    }

    @Override
    public UserPlanner findByUser(User user) throws ResourceNotFoundException {
        UserPlanner userPlanner;
        if (user.getUserPlanner() == null)
            userPlanner = new UserPlanner();
        else
            userPlanner = userPlannerRepository.findById(user.getUserPlanner().getId()).orElse(new UserPlanner());
        UserHealth userHealth = userHealthService.findById(user.getUserHealth().getId());
        userPlanner = getUserPlanner(userPlanner, userHealth);
        userPlanner = userPlannerRepository.save(userPlanner);
        userService.updateUserPlanner(userPlanner, user.getUsername());
        return userPlanner;
    }



}

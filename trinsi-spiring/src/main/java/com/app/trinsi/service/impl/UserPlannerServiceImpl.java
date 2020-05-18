package com.app.trinsi.service.impl;

import com.app.trinsi.model.EXERCISE_TYPE;
import com.app.trinsi.model.UserHealth;
import com.app.trinsi.model.UserPlanner;
import com.app.trinsi.repository.UserPlannerRepository;
import com.app.trinsi.service.UserPlannerService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPlannerServiceImpl implements UserPlannerService {

    private final UserPlannerRepository userPlannerRepository;
    private final KieContainer kieContainer;

    @Autowired
    public UserPlannerServiceImpl(UserPlannerRepository userPlannerRepository, KieContainer kieContainer) {
        this.userPlannerRepository = userPlannerRepository;
        this.kieContainer = kieContainer;
    }

    @Override
    public UserPlanner getUserPlanner(UserHealth userHealth) {
        UserPlanner userPlanner = new UserPlanner();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(userHealth);
        kieSession.insert(userPlanner);
        kieSession.fireAllRules();
        kieSession.dispose();
        return userPlanner;
    }
}

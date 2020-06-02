package com.app.trinsi.service.impl;

import com.app.trinsi.exceptions.MustUpdateHealthException;
import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.Exercise;
import com.app.trinsi.model.User;
import com.app.trinsi.model.UserHealth;
import com.app.trinsi.model.UserPlanner;
import com.app.trinsi.repository.UserPlannerRepository;
import com.app.trinsi.service.ExerciseService;
import com.app.trinsi.service.UserHealthService;
import com.app.trinsi.service.UserPlannerService;
import com.app.trinsi.service.UserService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Service
public class UserPlannerServiceImpl implements UserPlannerService {

    private final UserPlannerRepository userPlannerRepository;
    private final UserHealthService userHealthService;
    private final UserService userService;
    private final ExerciseService exerciseService;
    private final KieContainer kieContainer;

    @Autowired
    public UserPlannerServiceImpl(UserPlannerRepository userPlannerRepository, UserHealthService userHealthService,
                                  UserService userService, ExerciseService exerciseService, KieContainer kieContainer) {
        this.userPlannerRepository = userPlannerRepository;
        this.userHealthService = userHealthService;
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.kieContainer = kieContainer;
    }

    @Override
    public UserPlanner getUserPlanner(UserPlanner userPlanner, UserHealth userHealth) {
        KieSession kieSession = kieContainer.newKieSession("ksession-planner");
        kieSession.insert(userPlanner);
        kieSession.insert(userHealth);
        Collection<Exercise> exercises = exerciseService.findAll();
        for (Exercise e: exercises) {
            kieSession.insert(e);
        }
        kieSession.fireAllRules();
        kieSession.dispose();
        return userPlanner;
    }

    @Override
    public UserPlanner findByUser(User user) throws ResourceNotFoundException, MustUpdateHealthException {
        UserPlanner userPlanner;
        if (user.getUserPlanner() == null)
            userPlanner = new UserPlanner();
        else
            userPlanner = userPlannerRepository.findById(user.getUserPlanner().getId()).orElse(new UserPlanner());
        UserHealth userHealth = userHealthService.findById(user.getUserHealth().getId());
        if (userHealth.isPlannerTaken()) {
            return userPlanner;
        }
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(userHealth.getLastChanged());
        c.add(Calendar.DATE, 7);
        Date seven = c.getTime();
        seven.setHours(0);
        seven.setMinutes(0);
        seven.setSeconds(0);
        if (!today.before(seven)) {
            throw new MustUpdateHealthException();
        }
        userPlanner.setExercises(new HashSet<>());
        userPlanner = getUserPlanner(userPlanner, userHealth);
        userPlanner = userPlannerRepository.save(userPlanner);
        if (user.getUserPlanner() == null)
            userService.updateUserPlanner(userPlanner, user.getUsername());
        userHealthService.setPlannerIsTaken(userHealth);
        return userPlanner;
    }


}

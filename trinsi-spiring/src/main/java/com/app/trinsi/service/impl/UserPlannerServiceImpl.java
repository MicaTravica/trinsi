package com.app.trinsi.service.impl;

import com.app.trinsi.exceptions.MustUpdateHealthException;
import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.*;
import com.app.trinsi.repository.UserPlannerRepository;
import com.app.trinsi.service.ExerciseService;
import com.app.trinsi.service.UserHealthService;
import com.app.trinsi.service.UserPlannerService;
import com.app.trinsi.service.UserService;
import org.drools.core.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("planner").setFocus();
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
        userPlanner.setExercises(new ArrayList<>());
        userPlanner = getUserPlanner(userPlanner, userHealth);
        userPlanner = userPlannerRepository.save(userPlanner);
        if (user.getUserPlanner() == null)
            userService.updateUserPlanner(userPlanner, user.getUsername());
        userHealthService.setPlannerIsTaken(userHealth);
        return userPlanner;
    }

    @Override
    public Collection<UserPlanner> findAll() {
        return userPlannerRepository.findAll();
    }

    @Override
    public Collection<MissingExercises> reports(HashSet<CATEGORY> categories, HashSet<EXERCISE_TYPE> exerciseTypes) {
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("reports").setFocus();

        if (categories.size() == 0) {
            kieSession.insert(CATEGORY.BEGINNER);
            kieSession.insert(CATEGORY.MIDDLE);
            kieSession.insert(CATEGORY.ADVANCED);
        }
        else {
            for (CATEGORY category: categories) {
                kieSession.insert(category);
            }
        }

        if (exerciseTypes.size() == 0) {
            kieSession.insert(EXERCISE_TYPE.STRETCHES);
            kieSession.insert(EXERCISE_TYPE.STRENGTHS);
            kieSession.insert(EXERCISE_TYPE.CARDIO);
            kieSession.insert(EXERCISE_TYPE.WEIGHT_LOSS);
        }
        else {
            for (EXERCISE_TYPE exerciseType: exerciseTypes) {
                kieSession.insert(exerciseType);
            }
        }

        Collection<Exercise> exercises = exerciseService.findAll();
        for (Exercise exercise: exercises) {
            kieSession.insert(exercise);
        }

        Collection<UserPlanner> userPlanners = findAll();
        for (UserPlanner userPlanner: userPlanners) {
            kieSession.insert(userPlanner);
        }

        kieSession.fireAllRules();
        Collection<MissingExercises> missingExercises =
                (Collection<MissingExercises>) kieSession.getObjects(new ClassObjectFilter(MissingExercises.class));

        return  missingExercises;
    }

}

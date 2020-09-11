package com.app.trinsi.service.impl;

import com.app.trinsi.exceptions.MustUpdateHealthException;
import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.mapper.QuestionnaireMapper;
import com.app.trinsi.model.*;
import com.app.trinsi.repository.UserPlannerRepository;
import com.app.trinsi.service.ExerciseService;
import com.app.trinsi.service.UserHealthService;
import com.app.trinsi.service.UserPlannerService;
import com.app.trinsi.service.UserService;
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
    public UserPlanner findById(Long id) throws ResourceNotFoundException {
        return userPlannerRepository.findById(id).orElseThrow(() -> new  ResourceNotFoundException("Planner"));
    }

    @Override
    public UserPlanner createPlanner(User user, UserHealth userHealth, QuestionnairePA questionnairePA) throws
            ResourceNotFoundException {
        UserPlanner userPlanner = new UserPlanner();
        userPlanner.setMusclesGroupDates(new ArrayList<>());
        for (MUSCLES_GROUP m: MUSCLES_GROUP.values()) {
            userPlanner.addMusclesGroupDate(m);
        }
        return createUpdatePlanner(userPlanner, userHealth, questionnairePA, user.getUsername());
    }

    @Override
    public UserPlanner updatePlanner(User user, UserHealth userHealth) throws ResourceNotFoundException {
        UserPlanner userPlanner = findById(user.getUserPlanner().getId());
        return createUpdatePlanner(userPlanner, userHealth, null, user.getUsername());
    }

    @Override
    public UserPlanner createUpdatePlanner(UserPlanner userPlanner, UserHealth userHealth,
            QuestionnairePA questionnairePA, String username) throws ResourceNotFoundException {
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("planner").setFocus();
        kieSession.insert(userPlanner);
        kieSession.insert(userHealth);
        if (questionnairePA != null) {
            kieSession.insert(questionnairePA);
        }
        kieSession.fireAllRules();
        kieSession.dispose();

        userPlanner = userPlannerRepository.save(userPlanner);
        userService.updateUserPlanner(userPlanner, username);
        return userPlanner;
    }

    @Override
    public UserPlanner findByUser(User user) throws ResourceNotFoundException, MustUpdateHealthException {
        UserPlanner userPlanner = findById(user.getUserPlanner().getId());
        UserHealth userHealth = userHealthService.findById(user.getUserHealth().getId());
        if (userHealth.isPlannerTaken()) {
            return userPlanner;
        }
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(userHealth.getLastChanged());
        c.add(Calendar.DATE, 28);
        Date seven = c.getTime();
        seven.setHours(0);
        seven.setMinutes(0);
        seven.setSeconds(0);
        if (!today.before(seven)) {
            throw new MustUpdateHealthException();
        }
        userPlanner = getUserPlanner(userPlanner, userHealth);
        userPlanner = userPlannerRepository.save(userPlanner);
        userHealthService.setPlannerIsTaken(userHealth);
        return userPlanner;
    }

    @Override
    public UserPlanner getUserPlanner(UserPlanner userPlanner, UserHealth userHealth) {
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("calculate").setFocus();
        userPlanner.setExercisesWarmUp(new ArrayList<>());
        userPlanner.setExercises(new ArrayList<>());
        userPlanner.setExercisesStretching(new ArrayList<>());
        kieSession.insert(userPlanner);
        kieSession.insert(userHealth);
        List<Exercise> exercises = (List<Exercise>) exerciseService.findAll();
        Random rand = new Random();
        int numberOfElements = exercises.size();
        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(exercises.size());
            kieSession.insert(exercises.get(randomIndex));
            exercises.remove(randomIndex);
        }
        kieSession.fireAllRules();
        kieSession.dispose();
        return userPlanner;
    }

    @Override
    public Collection<UserPlanner> findAll() {
        return userPlannerRepository.findAll();
    }

}

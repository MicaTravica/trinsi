package com.app.trinsi.service;

import com.app.trinsi.exceptions.MustUpdateHealthException;
import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.*;

import java.util.Collection;
import java.util.HashSet;

public interface UserPlannerService {

    UserPlanner getUserPlanner(UserPlanner userPlanner, UserHealth userHealth);

    UserPlanner findById(Long id) throws ResourceNotFoundException;

    UserPlanner createPlanner(User user, UserHealth userHealth, QuestionnairePA questionnairePA) throws ResourceNotFoundException;

    UserPlanner updatePlanner(User user, UserHealth userHealth) throws ResourceNotFoundException;

    UserPlanner createUpdatePlanner(UserPlanner userPlanner, UserHealth userHealth,
                                    QuestionnairePA questionnairePA, String username) throws ResourceNotFoundException;

    UserPlanner findByUser(User user) throws ResourceNotFoundException, MustUpdateHealthException;

    Collection<UserPlanner> findAll();
}

package com.app.trinsi.service;

import com.app.trinsi.exceptions.MustUpdateHealthException;
import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.*;

import java.util.Collection;
import java.util.HashSet;

public interface UserPlannerService {

    UserPlanner getUserPlanner(UserPlanner userPlanner, UserHealth userHealth);

    UserPlanner findByUser(User user) throws ResourceNotFoundException, MustUpdateHealthException;

    Collection<UserPlanner> findAll();

    Collection<MissingExercises> reports(HashSet<CATEGORY> categories, HashSet<EXERCISE_TYPE> exerciseTypes);
}

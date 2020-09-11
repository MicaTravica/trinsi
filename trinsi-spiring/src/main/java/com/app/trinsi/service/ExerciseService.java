package com.app.trinsi.service;

import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.*;

import java.util.Collection;

public interface ExerciseService {

    Exercise findOneById(Long id) throws ResourceNotFoundException;

    Exercise addExercise(Exercise exercise);

    Exercise updateExercise(Exercise updateExercise) throws ResourceNotFoundException;

    Collection<Exercise> search(String name, EXERCISE_TYPE exerciseType, INTENSITY intensity, MUSCLES_GROUP musclesGroup);

    Collection<Exercise> findAll();

}

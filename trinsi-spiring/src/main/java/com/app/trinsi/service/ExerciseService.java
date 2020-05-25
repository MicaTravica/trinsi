package com.app.trinsi.service;

import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.CATEGORY;
import com.app.trinsi.model.EXERCISE_TYPE;
import com.app.trinsi.model.Exercise;

import java.util.Collection;

public interface ExerciseService {

    Exercise findOneById(Long id) throws ResourceNotFoundException;

    Exercise addExercise(Exercise exercise);

    Exercise updateExercise(Exercise updateExercise) throws ResourceNotFoundException;

    Collection<Exercise> search(String name, EXERCISE_TYPE exerciseType, CATEGORY exerciseWeight);
}

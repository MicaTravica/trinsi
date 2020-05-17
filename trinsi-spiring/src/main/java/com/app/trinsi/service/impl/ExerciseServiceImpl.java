package com.app.trinsi.service.impl;

import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.CATEGORY;
import com.app.trinsi.model.EXERCISE_TYPE;
import com.app.trinsi.model.Exercise;
import com.app.trinsi.repository.ExerciseRepository;
import com.app.trinsi.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public Exercise findOneById(Long id) throws ResourceNotFoundException {
        return exerciseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Exercise"));
    }

    @Override
    public Exercise addExercise(Exercise exercise) {
        exercise.setId(null);
        return exerciseRepository.save(exercise);
    }

    @Override
    public Collection<Exercise> search(String name, EXERCISE_TYPE exerciseType, CATEGORY exerciseWeight) {
        return exerciseRepository.search(name, exerciseType, exerciseWeight);
    }
}

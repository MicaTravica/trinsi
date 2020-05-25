package com.app.trinsi.service.impl;

import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.CATEGORY;
import com.app.trinsi.model.EXERCISE_TYPE;
import com.app.trinsi.model.Exercise;
import com.app.trinsi.repository.ExerciseRepository;
import com.app.trinsi.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

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
    public Exercise updateExercise(Exercise updateExercise) throws ResourceNotFoundException {
        Exercise exercise = findOneById(updateExercise.getId());
        exercise.update(updateExercise);
        return exerciseRepository.save(exercise);
    }

    @Override
    public Page<Exercise> search(String name, EXERCISE_TYPE exerciseType, CATEGORY exerciseWeight, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 30, Sort.by("name").ascending());
        return exerciseRepository.search(name, exerciseType, exerciseWeight, pageable);
    }


}

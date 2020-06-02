package com.app.trinsi.service.impl;

import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.CATEGORY;
import com.app.trinsi.model.EXERCISE_TYPE;
import com.app.trinsi.model.Exercise;
import com.app.trinsi.repository.ExerciseRepository;
import com.app.trinsi.service.ExerciseService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final KieContainer kieContainer;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, KieContainer kieContainer) {
        this.exerciseRepository = exerciseRepository;
        this.kieContainer = kieContainer;
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
    public Collection<Exercise> search(String name, EXERCISE_TYPE exerciseType, CATEGORY exerciseWeight) {
        Collection<Exercise> exercises = findAll();
        KieSession kieSession = kieContainer.newKieSession("ksession-search");
        for (Exercise exercise: exercises) {
            kieSession.insert(exercise);
        }
        kieSession.getAgenda().getAgendaGroup("search-exercises").setFocus();
        kieSession.setGlobal("gName", name);
        kieSession.setGlobal("gExerciseType", exerciseType);
        kieSession.setGlobal("gExerciseWeight", exerciseWeight);
        kieSession.setGlobal("exercises", new ArrayList<Exercise>());
        kieSession.fireAllRules();
        ArrayList<Exercise> result = (ArrayList<Exercise>) kieSession.getGlobal("exercises");
        kieSession.dispose();
        return result;
    }

    @Override
    public Collection<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

}

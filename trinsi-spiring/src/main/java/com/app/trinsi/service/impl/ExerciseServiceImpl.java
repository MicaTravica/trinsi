package com.app.trinsi.service.impl;

import com.app.trinsi.exceptions.ResourceNotFoundException;
import com.app.trinsi.model.*;
import com.app.trinsi.repository.ExerciseRepository;
import com.app.trinsi.service.ExerciseService;
import com.app.trinsi.service.PictureService;
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
    private final PictureService pictureService;
    private final KieContainer kieContainer;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, PictureService pictureService,
                               KieContainer kieContainer) {
        this.exerciseRepository = exerciseRepository;
        this.pictureService = pictureService;
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
        System.out.println("cao1");
        Exercise exercise = findOneById(updateExercise.getId());
        System.out.println("cao2");
        List<Picture> pictures = exercise.getPictures();
        System.out.println("cao3");
        exercise.update(updateExercise);
        System.out.println("ca4");
        Exercise savedExercise = exerciseRepository.save(exercise);
        System.out.println("cao5");
        for (Picture picture: pictures){
            System.out.println("caooooo");
            if (exercise.getPictures().stream().noneMatch(p -> p.getId().equals(picture.getId()) &&
                    p.getName().equals(picture.getName()) && p.getPath().equals(picture.getPath()))) {
                System.out.println("eeeeeeeeeee");
                pictureService.deletePicture(picture.getId());
            }
        }
        System.out.println("cao6");
        return savedExercise;
    }

    @Override
    public Collection<Exercise> search(String name, EXERCISE_TYPE exerciseType, INTENSITY intensity,
                                       MUSCLES_GROUP musclesGroup) {
        KieSession kieSession = kieContainer.newKieSession("cepRealtimeClock");
        kieSession.getAgenda().getAgendaGroup("search").setFocus();
        Collection<Exercise> exercises = findAll();
        for (Exercise exercise: exercises) {
            kieSession.insert(exercise);
        }
        kieSession.setGlobal("gName", name);
        kieSession.setGlobal("gExerciseType", exerciseType);
        kieSession.setGlobal("gIntensity", intensity);
        kieSession.setGlobal("gMusclesGroup", musclesGroup);
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

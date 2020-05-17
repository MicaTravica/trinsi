package com.app.trinsi.mapper;

import com.app.trinsi.dto.ExerciseDTO;
import com.app.trinsi.model.Exercise;

public class ExerciseMapper {

    public static ExerciseDTO toDTO(Exercise exercise) {
        return new ExerciseDTO(exercise.getId(), exercise.getName(), exercise.getDescription(),
                exercise.getExerciseType(), exercise.getExerciseWeight());
    }

    public static Exercise toExercise(ExerciseDTO exerciseDTO) {
        return new Exercise(exerciseDTO.getId(), exerciseDTO.getName(), exerciseDTO.getDescription(),
                exerciseDTO.getExerciseType(), exerciseDTO.getExerciseWeight());
    }
}

package com.app.trinsi.mapper;

import com.app.trinsi.dto.ExerciseDTO;
import com.app.trinsi.model.Exercise;

import java.util.stream.Collectors;

public class ExerciseMapper {

    public static ExerciseDTO toDTO(Exercise exercise) {
        return new ExerciseDTO(exercise.getId(), exercise.getName(), exercise.getDescription(),
                exercise.getExerciseType(), exercise.getIntensity(), exercise.getMusclesGroup(),
                exercise.getPictures().stream().map(PictureMapper::toDTO).collect(Collectors.toList()));
    }

    public static Exercise toExercise(ExerciseDTO exerciseDTO) {
        return new Exercise(exerciseDTO.getId(), exerciseDTO.getName(), exerciseDTO.getDescription(),
                exerciseDTO.getExerciseType(), exerciseDTO.getIntensity(), exerciseDTO.getMusclesGroup(),
                exerciseDTO.getPictures().stream().map(PictureMapper::toPicture).collect(Collectors.toList()));
    }
}

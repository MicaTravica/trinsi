package com.app.trinsi.mapper;

import com.app.trinsi.dto.UserPlannerDTO;
import com.app.trinsi.model.UserPlanner;

import java.util.stream.Collectors;

public class UserPlannerMapper {

    public static UserPlannerDTO toDTO(UserPlanner userPlanner) {
        return new UserPlannerDTO(userPlanner.getId(), userPlanner.getActivityLevel(), userPlanner.getNutritionLevel(),
                userPlanner.getBloodPressureClassification(), userPlanner.getIntensity(), userPlanner.getSetsMuscular(),
                userPlanner.getRepetitionMuscular(), userPlanner.getNumOfMinutesAerobic(),
                userPlanner.getNumOfMinutesAerobicWarmUp(), userPlanner.getNumOfSecondsStretching(),
                userPlanner.getLowerPulseLimit(), userPlanner.getUpperPulseLimit(),
                userPlanner.getExercisesWarmUp().stream().map(ExerciseMapper::toDTO).collect(Collectors.toList()),
                userPlanner.getExercises().stream().map(ExerciseMapper::toDTO).collect(Collectors.toList()),
                userPlanner.getExercisesStretching().stream().map(ExerciseMapper::toDTO).collect(Collectors.toList()));
    }
}

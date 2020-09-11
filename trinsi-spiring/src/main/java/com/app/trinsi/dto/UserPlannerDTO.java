package com.app.trinsi.dto;

import com.app.trinsi.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPlannerDTO {

    private Long id;
    private ACTIVITY_LEVEL activityLevel;
    private NUTRITION_LEVEL nutritionLevel;
    private BLOOD_PRESSURE_CLASSIFICATION bloodPressureClassification;
    private INTENSITY intensity;
    private int setsMuscular;
    private int repetitionMuscular;
    private int numOfMinutesAerobic;
    private int numOfMinutesAerobicWarmUp;
    private int numOfSecondsStretching;
    private int lowerPulseLimit;
    private int upperPulseLimit;
    private Collection<ExerciseDTO> exercisesWarmUp;
    private Collection<ExerciseDTO> exercises;
    private Collection<ExerciseDTO> exercisesStretching;

}

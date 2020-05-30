package com.app.trinsi.dto;

import com.app.trinsi.model.CATEGORY;
import com.app.trinsi.model.EXERCISE_TYPE;
import com.app.trinsi.model.HEALTH_CONDITION;
import com.app.trinsi.model.PHYSICAL_CHARACTERISTICS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPlannerDTO {

    private Long id;
    private CATEGORY category;
    private int numCategory;
    private PHYSICAL_CHARACTERISTICS physicalCharacteristics;
    private HEALTH_CONDITION healthCondition;
    private int numOfExercise;
    private int repetition;
    private EXERCISE_TYPE exerciseType;
    private Collection<ExerciseDTO> exercises;

}

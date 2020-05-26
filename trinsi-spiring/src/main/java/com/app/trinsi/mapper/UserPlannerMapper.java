package com.app.trinsi.mapper;

import com.app.trinsi.dto.UserPlannerDTO;
import com.app.trinsi.model.UserPlanner;

public class UserPlannerMapper {

    public static UserPlannerDTO toDTO(UserPlanner userPlanner) {
        return new UserPlannerDTO(userPlanner.getId(), userPlanner.getCategory(), userPlanner.getNumCategory(),
                userPlanner.getPhysicalCharacteristics(), userPlanner.getHealthCondition(),
                userPlanner.getNumOfExercise(), userPlanner.getRepetition(), userPlanner.getExerciseType());
    }
}

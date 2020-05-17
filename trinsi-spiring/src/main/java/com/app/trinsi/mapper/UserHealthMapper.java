package com.app.trinsi.mapper;

import com.app.trinsi.dto.ExerciseDTO;
import com.app.trinsi.dto.UserHealthDTO;
import com.app.trinsi.model.Exercise;
import com.app.trinsi.model.UserHealth;

public class UserHealthMapper {

    public static UserHealthDTO toDTO(UserHealth userHealth) {
        return new UserHealthDTO(userHealth.getGender(), userHealth.getDateOfBirth(), userHealth.getHeight(),
                userHealth.getWeight(), userHealth.getUpperBloodPressure(), userHealth.getLowerBloodPressure(),
                userHealth.getPulse(), userHealth.getHoursOfExercise());
    }

    public static Object toHealth(UserHealthDTO userHealthDTO) {
        return new UserHealth(null, userHealthDTO.getGender(), userHealthDTO.getDateOfBirth(),
                userHealthDTO.getHeight(), userHealthDTO.getWeight(), userHealthDTO.getUpperBloodPressure(),
                userHealthDTO.getLowerBloodPressure(), userHealthDTO.getPulse(), userHealthDTO.getHoursOfExercise());
    }
}

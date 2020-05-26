package com.app.trinsi.mapper;

import com.app.trinsi.dto.UserHealthDTO;
import com.app.trinsi.model.UserHealth;

public class UserHealthMapper {

    public static UserHealthDTO toDTO(UserHealth userHealth) {
        return new UserHealthDTO(userHealth.getId(), userHealth.getGender(), userHealth.getYears(), userHealth.getHeight(),
                userHealth.getWeight(), userHealth.getUpperBloodPressure(), userHealth.getLowerBloodPressure(),
                userHealth.getPulse(), userHealth.getHoursOfExercise());
    }

    public static UserHealth toHealth(UserHealthDTO userHealthDTO) {
        return new UserHealth(userHealthDTO.getId(), userHealthDTO.getGender(), userHealthDTO.getYears(),
                userHealthDTO.getHeight(), userHealthDTO.getWeight(), userHealthDTO.getUpperBloodPressure(),
                userHealthDTO.getLowerBloodPressure(), userHealthDTO.getPulse(), userHealthDTO.getHoursOfExercise());
    }
}

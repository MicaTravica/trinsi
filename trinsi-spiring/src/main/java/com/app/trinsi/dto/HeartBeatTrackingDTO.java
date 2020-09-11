package com.app.trinsi.dto;

import com.app.trinsi.model.BLOOD_PRESSURE_CLASSIFICATION;
import com.app.trinsi.model.NUTRITION_LEVEL;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeartBeatTrackingDTO {

    @NotNull(message = "User id can not be null")
    private Long userId;

    @NotNull(message = "Current pulse can not be null")
    @Positive(message = "Current pulse must be positive number")
    private int currentPulse;

    @NotNull(message = "Lower pulse limit can not be null")
    @Positive(message = "Lower pulse limit be positive number")
    private int lowerPulseLimit;

    @NotNull(message = "Upper pulse limit can not be null")
    @Positive(message = "Upper pulse limitt be positive number")
    private int upperPulseLimit;

    @NotNull(message = "Blood pressure classification can not be null")
    private BLOOD_PRESSURE_CLASSIFICATION bloodPressureClassification;

    @NotNull(message = "Nutrition level can not be null")
    private NUTRITION_LEVEL nutritionLevel;
}

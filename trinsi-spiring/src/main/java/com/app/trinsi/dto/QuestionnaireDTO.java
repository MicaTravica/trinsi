package com.app.trinsi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireDTO {


    @NotNull(message = "Vigorous days can not be null")
    @Min(value = 0, message = "Vigorous days min is {value}")
    @Max(value = 7, message = "Vigorous days max is {value}")
    private int vigorousDays;

    @NotNull(message = "Vigorous minutes can not be null")
    @Min(value = 0, message = "Vigorous minutes min is {value}")
    private int vigorousMinutes;

    @NotNull(message = "Moderate days can not be null")
    @Min(value = 0, message = "Moderate days min is {value}")
    @Max(value = 7, message = "Moderate days max is {value}")
    private int moderateDays;

    @NotNull(message = "Moderate minutes can not be null")
    @Min(value = 0, message = "Moderate minutes min is {value}")
    private int moderateMinutes;

    @NotNull(message = "Walking days can not be null")
    @Min(value = 0, message = "Walking days min is {value}")
    @Max(value = 7, message = "Walking days max is {value}")
    private int walkingDays;

    @NotNull(message = "Walking minutes can not be null")
    @Min(value = 0, message = "Walking minutes min is {value}")
    private int walkingMinutes;

    @AssertTrue(message = "Vigorous minutes min is 1")
    public boolean validateVigorous() {
        if (vigorousDays > 0 && vigorousMinutes < 1)
            return false;
        return true;
    }

    @AssertTrue(message = "Moderate minutes min is 1")
    public boolean validateModerate() {
        if (moderateDays > 0 && moderateMinutes < 1)
            return false;
        return true;
    }

    @AssertTrue(message = "Walking minutes min is 10")
    public boolean validateWalking() {
        if (walkingDays > 0 && walkingMinutes < 10)
            return false;
        return true;
    }

}

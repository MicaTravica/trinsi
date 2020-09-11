package com.app.trinsi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserHealthDTO {

    private Long id;

    @NotNull(message = "Years can not be null")
    @Min(value = 0, message = "Years min is {value}")
    private int years;

    @NotNull(message = "Height can not be null")
    @Min(value = 0, message = "Height min is {value}")
    private double height;

    @NotNull(message = "Weight can not be null")
    @Min(value = 0, message = "Weight min is {value}")
    private double weight;

    @NotNull(message = "Upper blood pressure can not be null")
    @Min(value = 0, message = "Upper blood pressure min is {value}")
    private int upperBloodPressure;

    @NotNull(message = "Lower blood pressure can not be null")
    @Min(value = 0, message = "Lower blood pressure min is {value}")
    private int lowerBloodPressure;

    @NotNull(message = "Pulse can not be null")
    @Min(value = 0, message = "Pulse min is {value}")
    private int pulse;

    private Date lastChanged;
    private boolean plannerTaken;
    @Valid
    private QuestionnaireDTO questionnaire;
}

package com.app.trinsi.dto;

import com.app.trinsi.model.GENDER;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserHealthDTO {

    private GENDER gender;
    private int years;
    private double height;
    private double weight;
    private int upperBloodPressure;
    private int lowerBloodPressure;
    private int pulse;
    private int hoursOfExercise;
}

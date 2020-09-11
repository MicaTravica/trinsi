package com.app.trinsi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnairePA {

    private int vigorousDays;
    private int vigorousMinutes;
    private double vigorous;
    private int moderateDays;
    private int moderateMinutes;
    private double moderate;
    private int walkingDays;
    private int walkingMinutes;
    private double walking;

}

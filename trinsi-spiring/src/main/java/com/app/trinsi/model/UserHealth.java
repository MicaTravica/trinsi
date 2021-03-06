package com.app.trinsi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserHealth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int years;
    private double height;
    private double weight;
    private int upperBloodPressure;
    private int lowerBloodPressure;
    private int pulse;
    private int minutesOfExercise;
    private Date lastChanged;
    private boolean plannerTaken;

    public void update(UserHealth updateHealth) {
        this.years = updateHealth.getYears();
        this.height = updateHealth.getHeight();
        this.weight = updateHealth.getWeight();
        this.upperBloodPressure = updateHealth.getUpperBloodPressure();
        this.lowerBloodPressure = updateHealth.getLowerBloodPressure();
        this.pulse = updateHealth.getPulse();
        this.lastChanged = new Date();
        this.plannerTaken = false;
        this.minutesOfExercise = 0;
    }
}

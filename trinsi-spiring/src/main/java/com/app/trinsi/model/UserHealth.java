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
    private GENDER gender;
    private int years;
    private double height;
    private double weight;
    private int upperBloodPressure;
    private int lowerBloodPressure;
    private int pulse;
    private int hoursOfExercise;
}

package com.app.trinsi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserPlanner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean old;
    private ACTIVITY_LEVEL activityLevel;
    private double bmi;
    private NUTRITION_LEVEL nutritionLevel;
    private BLOOD_PRESSURE_CLASSIFICATION bloodPressureClassification;
    private INTENSITY intensity;
    private int numIntensity;
    private int numOfExerciseMuscular;
    private int setsMuscular;
    private int repetitionMuscular;
    private int numOfMinutesAerobic;
    private int numOfMinutesAerobicWarmUp;
    private int numOfSecondsStretching;
    private MUSCLES_GROUP musclesGroup;
    private int lowerPulseLimit;
    private int upperPulseLimit;

    @OneToMany(cascade=CascadeType.ALL)
    private List<MusclesGroupDate> musclesGroupDates;

    @ManyToMany
    @JoinTable(
            name = "exercises_warm_up",
            joinColumns = @JoinColumn(name = "user_planner_id"),
            inverseJoinColumns = @JoinColumn(name = "excercise_id"))
    @CollectionId(columns = @Column(name = "id"), type = @Type(type = "long"), generator = "sequence")
    private List<Exercise> exercisesWarmUp;

    @ManyToMany
    @JoinTable(
            name = "exercises",
            joinColumns = @JoinColumn(name = "user_planner_id"),
            inverseJoinColumns = @JoinColumn(name = "excercise_id"))
    @CollectionId(columns = @Column(name = "id"), type = @Type(type = "long"), generator = "sequence")
    private List<Exercise> exercises;

    @ManyToMany
    @JoinTable(
            name = "exercises_strectching",
            joinColumns = @JoinColumn(name = "user_planner_id"),
            inverseJoinColumns = @JoinColumn(name = "excercise_id"))
    @CollectionId(columns = @Column(name = "id"), type = @Type(type = "long"), generator = "sequence")
    private List<Exercise> exercisesStretching;

    public void addMusclesGroupDate (MUSCLES_GROUP m) {
        this.musclesGroupDates.add(new MusclesGroupDate(null, m, null));
    }

}

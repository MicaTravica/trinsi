package com.app.trinsi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private CATEGORY category;
    private int numCategory;
    private PHYSICAL_CHARACTERISTICS physicalCharacteristics;
    private HEALTH_CONDITION healthCondition;
    private int numOfExercise;
    private int repetition;
    private EXERCISE_TYPE exerciseType;

    @ManyToMany
    @JoinTable(
            name = "exercises",
            joinColumns = @JoinColumn(name = "user_planner_id"),
            inverseJoinColumns = @JoinColumn(name = "excercise_id"))
    private Set<Exercise> exercises;
}

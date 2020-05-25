package com.app.trinsi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name can not be empty string")
    @NotNull(message = "Name can not be null")
    @Column(unique = true)
    private String name;

    @NotBlank(message = "Description can not be empty string")
    @NotNull(message = "Description can not be null")
    private String description;

    @NotNull(message = "Exercise type can not be null")
    @Enumerated(EnumType.STRING)
    private EXERCISE_TYPE exerciseType;

    @NotNull(message = "Exercise weight can not be null")
    @Enumerated(EnumType.STRING)
    private CATEGORY exerciseWeight;

    public void update(Exercise updateExercise) {
        this.name = updateExercise.getName();
        this.description = updateExercise.getDescription();
        this.exerciseType = updateExercise.getExerciseType();
        this.exerciseWeight = updateExercise.getExerciseWeight();
    }
}
